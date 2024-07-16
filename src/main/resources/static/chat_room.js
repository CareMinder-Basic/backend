const apiPrefix = '/api'; // API prefix
let stompClient = null;
let currentRoomId = null;
let memberId = 1; // 예시 멤버 ID
let subscriptions = {};

document.addEventListener('DOMContentLoaded', function () {
    promptForMemberId();
    loadChatRooms();
});

async function promptForMemberId() {
    let inputMemberId = prompt("사용자 ID를 입력하세요:");

    // 사용자가 취소를 누르거나 빈 값을 입력한 경우 처리
    if (inputMemberId === null || inputMemberId.trim() === "") {
        alert("사용자 ID가 유효하지 않습니다. 페이지를 새로고침하여 다시 시도하세요.");
        return;
    }

    // 입력된 memberId를 전역 변수에 할당
    memberId = parseInt(inputMemberId); // 예시로 parseInt를 사용하여 숫자로 변환
    if (isNaN(memberId)) {
        alert("유효하지 않은 사용자 ID입니다. 페이지를 새로고침하여 다시 시도하세요.");
        memberId = null; // memberId 초기화
    } else {
        console.log("사용자 ID로 설정된 값:", memberId);
    }
}

async function loadChatRooms() {
    try {
        const response = await axios.get(apiPrefix + '/chat-rooms');
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
        roomElement.textContent = room.name;

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
        const response = await axios.get(apiPrefix + '/check-subscribe', {
            params: {
                memberId: memberId,
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
            memberId: memberId,
            roomId: roomId
        };

        const response = await axios.post(apiPrefix + '/subscribe', subscriptionRequest);
        alert(response.data);

        if (!stompClient) {
            await connectToWebSocket();
        }

        subscriptions[roomId] = stompClient.subscribe(`/topic/chat/${roomId}`, (message) => {
            const parsedMessage = JSON.parse(message.body);
            showMessage(parsedMessage.memberId, parsedMessage.content);
        });

        loadChatRooms();
    } catch (error) {
        handleError(error);
    }
}

async function unsubscribeFromRoom(roomId, event) {
    try {
        const unsubscribeRequest = {
            memberId: memberId,
            roomId: roomId
        };

        const response = await axios.delete(apiPrefix + '/unsubscribe', { data: unsubscribeRequest });
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
        const response = await axios.get(apiPrefix + '/check-subscribe', {
            params: {
                memberId: memberId,
                roomId: roomId
            }
        });

        const isSubscribed = response.data;

        if (isSubscribed) {
            document.getElementById('chat-room-container').style.display = 'block';
            document.getElementById('chat-rooms').style.display = 'none';

            // Fetch chat history
            const messagesResponse = await axios.get(apiPrefix + '/chat-history/' + roomId);
            console.log(messagesResponse);
            const messages = messagesResponse.data.data;

            // Clear existing messages
            const messagesContainer = document.getElementById('messages');
            messagesContainer.innerHTML = '';

            // Display messages
            messages.forEach(message => {
                showMessage(message.memberId, message.content);
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

        stompClient.connect({}, () => {
            console.log('WebSocket 연결 성공');
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
        memberId: memberId,
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

function showMessage(id, content) {
    const messagesContainer = document.getElementById('messages');
    const messageElement = document.createElement('div');
    messageElement.textContent = "member" + id + " : " + content;
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
