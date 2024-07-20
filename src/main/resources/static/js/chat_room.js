const apiPrefix = '/api'; // API prefix
let stompClient = null;
let currentRoomId = null;
let token = null;
let subscriptions = {};

// Axios 인스턴스 생성
const authAxios = axios.create({
    baseURL: apiPrefix,
    headers: {
        'Content-Type': 'application/json'
    }
});

// 요청 인터셉터 추가
authAxios.interceptors.request.use(
    config => {
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

document.addEventListener('DOMContentLoaded', function () {
    checkTokenAndLoadChatRooms();
});

async function setToken(tokenVal) {
    localStorage.setItem("token", tokenVal);
    token = localStorage.getItem("token");
}

async function checkTokenAndLoadChatRooms() {

    if (!token) {
        const tokenVal = window.prompt("로그인이 필요합니다.");
        await setToken(tokenVal);
    }

    try {
        const response = await authAxios.get('/auth/info');
        console.log('토큰 정보:', response.data);
        displayUserInfo(response.data);
        loadChatRooms();
    } catch (error) {
        console.error('토큰 검증 실패:', error);
        alert("로그인이 필요합니다.");
    }
}

function displayUserInfo(userInfo) {
    const userInfoContainer = document.getElementById('user-info');
    userInfoContainer.innerHTML = `
        <p>Username: ${userInfo.name}</p>
        <p>Role: ${userInfo.role}</p>
    `;
}

async function createChatRoom() {
    const roomNameInput = document.getElementById('roomNameInput');
    const roomName = roomNameInput.value.trim();

    if (!roomName) {
        alert("채팅방 이름을 입력하세요.");
        return;
    }

    try {
        const response = await authAxios.post('/chat-rooms', {
            roomName: roomName
        });
        alert("채팅방이 생성되었습니다.");
        loadChatRooms(); // 채팅방 목록을 다시 불러옵니다.
    } catch (error) {
        handleError(error);
    }
}

async function loadChatRooms() {
    try {
        const response = await authAxios.get('/chat-rooms');
        const chatRooms = response.data.data;
        displayChatRooms(chatRooms);
    } catch (error) {
        console.error('Error:', error);
    }
}

async function displayChatRooms(chatRooms) {
    const chatRoomsContainer = document.getElementById('chat-rooms');
    chatRoomsContainer.innerHTML = '';

    for (const room of chatRooms) {
        const roomElement = document.createElement('div');
        roomElement.textContent = room.roomName;

        const button = await displayButton(room);
        if (button) {
            roomElement.appendChild(button);
        }

        const enterButton = document.createElement('button');
        enterButton.textContent = 'Enter Room';
        enterButton.onclick = (event) => enterChatRoom(room.id);

        roomElement.appendChild(enterButton);
        chatRoomsContainer.appendChild(roomElement);
    }
}

async function displayButton(room) {
    try {
        const response = await authAxios.get('/check-subscribe', {
            params: {
                roomId: room.id
            }
        });

        const isSubscribed = response.data;
        let button = null;

        if (isSubscribed) {
            button = document.createElement('button');
            button.textContent = 'Unsubscribe';
            button.onclick = (event) => unsubscribeFromRoom(room.id, event);

        } else {
            button = document.createElement('button');
            button.textContent = 'Subscribe';
            button.onclick = (event) => subscribeToRoom(room.id, event);
        }

        return button;
    } catch (error) {
        console.error('Error:', error);
        return null; // 에러 발생 시 null을 반환하여 처리
    }
}

async function subscribeToRoom(roomId) {
    try {
        const subscriptionRequest = {
            roomId: roomId
        };

        const response = await authAxios.post('/subscribe', subscriptionRequest);
        alert(response.data);

        if (!stompClient) {
            await connectToWebSocket();
        }

        subscriptions[roomId] = stompClient.subscribe(`/topic/chat/${roomId}`, (message) => {
            const parsedMessage = JSON.parse(message.body);
            showMessage(parsedMessage.senderName, parsedMessage.content);
        });

        loadChatRooms();
    } catch (error) {
        handleError(error);
    }
}

async function unsubscribeFromRoom(roomId, event) {
    try {
        const unsubscribeRequest = {
            roomId: roomId
        };

        const response = await authAxios.delete('/unsubscribe', { data: unsubscribeRequest });
        alert(response.data);

        if (stompClient) {
            stompClient.unsubscribe(`/topic/chat/${roomId}`);
            if (subscriptions[roomId]) {
                subscriptions[roomId].unsubscribe();
                delete subscriptions[roomId];
            }
        }

        loadChatRooms();
    } catch (error) {
        handleError(error);
    }
}

async function enterChatRoom(roomId) {
    currentRoomId = roomId;

    try {
        const response = await authAxios.get('/check-subscribe', {
            params: {
                roomId: roomId
            }
        });

        const isSubscribed = response.data;

        if (isSubscribed) {
            document.getElementById('chat-room-container').style.display = 'block';
            document.getElementById('chat-rooms').style.display = 'none';

            // Fetch chat history
            const messagesResponse = await authAxios.get('/chat-history/' + roomId);
            const messages = messagesResponse.data.data;

            // Clear existing messages
            const messagesContainer = document.getElementById('messages');
            messagesContainer.innerHTML = '';

            // Display messages
            messages.forEach(message => {
                showMessage(message.senderName, message.content);
            });
        } else {
            alert("채팅방을 먼저 구독하세요");
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

async function connectToWebSocket() {
    return new Promise((resolve, reject) => {
        const socket = new SockJS('/ws'); // WebSocket 엔드포인트
        stompClient = Stomp.over(socket);

        stompClient.connect({'Authorization': `Bearer ${token}`}, (frame) => {
            console.log('WebSocket 연결 성공');
            console.log('연결 정보: ', frame);
            resolve(stompClient);
        }, error => {
            console.error('WebSocket 연결 실패:', error);
            reject(error);
        });
    });
}

function sendMessage() {
    const messageInput = document.getElementById('messageInput');
    const message = {
        content: messageInput.value
    };

    if (!stompClient) {
        connectToWebSocket()
            .then(() => {
                stompClient.send(`/app/chat/${currentRoomId}`, {}, JSON.stringify(message));
                messageInput.value = '';
            })
            .catch(error => {
                console.error('Error:', error);
            });
    } else {
        stompClient.send(`/app/chat/${currentRoomId}`, {}, JSON.stringify(message));
        messageInput.value = '';
    }
}

function showMessage(senderName, content) {
    const messagesContainer = document.getElementById('messages');
    const messageElement = document.createElement('div');
    messageElement.textContent = senderName + " : " + content;
    messagesContainer.appendChild(messageElement);
}

function leaveRoom() {
    currentRoomId = null;
    document.getElementById('chat-room-container').style.display = 'none';
    document.getElementById('chat-rooms').style.display = 'block';

    // Clear all messages
    const messagesContainer = document.getElementById('messages');
    messagesContainer.innerHTML = '';
}

function handleError(error) {
    if (error.response) {
        const statusCode = error.response.status;
        const message = error.response.data;
        alert(`Error ${statusCode}: ${message}`);
    } else if (error.request) {
        alert('No response received from the server.');
    } else {
        alert(`Error: ${error.message}`);
    }
    console.error('Error:', error);
}
