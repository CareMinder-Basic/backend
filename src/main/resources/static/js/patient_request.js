const apiPrefix = '/api'; // API prefix
let stompClient = null;
let currentPatientRequestId = null;
let token = localStorage.getItem("token"); // 초기화 시 localStorage에서 토큰 값을 가져옴
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
    checkTokenAndLoadPatientRequests();
    loadStaffList();
});

async function setToken(tokenVal) {
    localStorage.setItem("token", tokenVal);
    token = tokenVal;
}

async function checkTokenAndLoadPatientRequests() {
    if (!token) {
        const tokenVal = window.prompt("로그인이 필요합니다.");
        if (tokenVal) {
            await setToken(tokenVal);
        } else {
            alert("토큰 값이 필요합니다. 페이지를 새로고침하고 다시 시도하세요.");
            return;
        }
    }

    try {
        console.log(token);
        const response = await authAxios.get('/auth/info');
        console.log('토큰 정보:', response.data);
        displayUserInfo(response.data);
        await loadPatientRequest();
    } catch (error) {
        console.error('토큰 검증 실패:', error);
        alert('토큰 검증에 실패했습니다. 페이지를 새로고침하고 다시 시도하세요.');
        // 토큰이 유효하지 않다면, 토큰을 초기화하고 재입력 요청
        localStorage.removeItem("token");
        token = null;
    }
}

function displayUserInfo(userInfo) {
    const userInfoContainer = document.getElementById('user-info');
    userInfoContainer.innerHTML = `
        <p>Username: ${userInfo.name}</p>
        <p>Role: ${userInfo.role}</p>
    `;
}

async function createPatientRequest() {
    const patientRequestInput = document.getElementById('patientRequestInput');
    const staffSelect = document.getElementById('staffSelect');
    const requestContent = patientRequestInput.value.trim();
    const staffId = staffSelect.value;

    if (!isNotNullAndUndefined(requestContent)) {
        alert("요청을 입력하세요.");
        return;
    }

    if (!isNotNullAndUndefined(staffId)) {
        alert("직원을 선택하세요.");
        return;
    }

    try {
        const patientRequestAppender = {staffId: staffId, content: requestContent}
        const response = await authAxios.post('/patient-request', patientRequestAppender);
        alert("요청이 생성되었습니다.");
        await loadPatientRequest(); // 채팅방 목록을 다시 불러옵니다.
    } catch (error) {
        handleError(error);
    }
}

async function loadStaffList() {
    console.log("호출")
    try {
        const response = await authAxios.get('/staff/list');
        const staffList = response.data.data;
        console.log(staffList)
        const staffSelect = document.getElementById('staffSelect');

        staffList.forEach(staff => {
            const option = document.createElement('option');
            option.value = staff.staffId;
            option.textContent = `${staff.name} (${staff.staffRole})`;
            staffSelect.appendChild(option);
        });
    } catch (error) {
        console.error('Error loading staff list:', error);
    }
}

async function loadPatientRequest() {
    try {
        const response = await authAxios.get('/patient-request');
        const patientRequests = response.data.data;
        await displayPatientRequests(patientRequests);
    } catch (error) {
        console.error('Error:', error);
    }
}

async function displayPatientRequests(patientRequests) {
    const patientRequestContainer = document.getElementById('patient-requests');
    patientRequestContainer.innerHTML = '';

    for (const patientRequest of patientRequests) {
        const roomElement = document.createElement('div');
        roomElement.textContent = patientRequest.content;

        const button = await displayButton(patientRequest);
        if (button) {
            roomElement.appendChild(button);
        }

        const enterButton = document.createElement('button');
        enterButton.textContent = '채팅 입장';
        enterButton.onclick = () => enterPatientRequest(patientRequest.id);

        roomElement.appendChild(enterButton);
        patientRequestContainer.appendChild(roomElement);
    }
}

async function displayButton(patientRequest) {
    try {
        const response = await authAxios.get('patient-request/check-subscribe', { params: { patientRequestId: patientRequest.id } });
        const isSubscribed = response.data;
        let button = document.createElement('button');

        if (isSubscribed) {
            button.textContent = '요청 종료';
            button.onclick = (event) => unsubscribeFromRoom(patientRequest.id, event);
        } else {
            button.textContent = '요청 수락';
            button.onclick = (event) => subscribeToRoom(patientRequest.id, event);
        }

        return button;
    } catch (error) {
        console.error('Error:', error);
        return null; // 에러 발생 시 null을 반환하여 처리
    }
}

async function subscribeToRoom(patientRequestId) {
    try {
        const subscriptionRequest = { patientRequestId: patientRequestId };
        const response = await authAxios.post('patient-request/subscribe', subscriptionRequest);
        alert(response.data);

        if (!stompClient) {
            await connectToWebSocket();
        }

        subscriptions[patientRequestId] = stompClient.subscribe(`/topic/chat/${patientRequestId}`, (message) => {
            const parsedMessage = JSON.parse(message.body);
            console.log(parsedMessage);
            showMessage(parsedMessage.role, parsedMessage.senderName, parsedMessage.content, parsedMessage.createdAt);
        });

        await loadPatientRequest();
    } catch (error) {
        handleError(error);
    }
}

async function unsubscribeFromRoom(roomId, event) {
    try {
        const unsubscribeRequest = { roomId };
        const response = await authAxios.delete('patient-request/unsubscribe', { data: unsubscribeRequest });
        alert(response.data);

        if (stompClient) {
            stompClient.unsubscribe(`/topic/chat/${roomId}`);
            if (subscriptions[roomId]) {
                subscriptions[roomId].unsubscribe();
                delete subscriptions[roomId];
            }
        }

        await loadPatientRequest();
    } catch (error) {
        handleError(error);
    }
}

async function enterPatientRequest(patientRequestId) {
    currentPatientRequestId = patientRequestId;

    try {
        const response = await authAxios.get('patient-request/check-subscribe', { params: { patientRequestId: patientRequestId } });
        const isSubscribed = response.data;

        if (isSubscribed) {
            document.getElementById('patient-request-container').style.display = 'block';
            document.getElementById('patient-requests').style.display = 'none';

            // Fetch chat history
            const messagesResponse = await authAxios.get(`/chat-history/${patientRequestId}`);
            const messages = messagesResponse.data.data;

            // Clear existing messages
            const messagesContainer = document.getElementById('messages');
            messagesContainer.innerHTML = '';

            // Display messages
            messages.forEach(message => {
                console.log(message);
                showMessage(message.role, message.senderName, message.content, message.createdAt);
            });
        } else {
            alert("채팅방을 먼저 구독하세요");
        }
    } catch (error) {
        console.error('Error:', error.message);
    }
}

async function connectToWebSocket() {
    return new Promise((resolve, reject) => {
        const socket = new SockJS('/ws'); // WebSocket 엔드포인트
        stompClient = Stomp.over(socket);

        stompClient.connect({ 'Authorization': `Bearer ${token}` }, (frame) => {
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
    const message = { content: messageInput.value };

    if (!stompClient) {
        connectToWebSocket()
            .then(() => {
                stompClient.send(`/app/chat/${currentPatientRequestId}`, {}, JSON.stringify(message));
                messageInput.value = '';
            })
            .catch(error => {
                console.error('Error:', error.response.data.message);
            });
    } else {
        stompClient.send(`/app/chat/${currentPatientRequestId}`, {}, JSON.stringify(message));
        messageInput.value = '';
    }
}

function showMessage(role, senderName, content, createdAt) {
    const messagesContainer = document.getElementById('messages');
    const messageElement = document.createElement('div');
    const timeAgoText = timeAgo(createdAt);
    if(role === "STAFF"){
        messageElement.textContent = `(직원) ${senderName} : ${content} - ${timeAgoText}`;
    }else if(role === "TABLET"){
        messageElement.textContent = `(태블릿) ${senderName} : ${content} - ${timeAgoText}`;
    }
    messagesContainer.appendChild(messageElement);
}

function leaveRoom() {
    currentPatientRequestId = null;
    document.getElementById('patient-request-container').style.display = 'none';
    document.getElementById('patient-requests').style.display = 'block';

    // Clear all messages
    const messagesContainer = document.getElementById('messages');
    messagesContainer.innerHTML = '';
}

function handleError(error) {
    if (error.response) {
        const statusCode = error.response.status;
        const message = error.response.data.message;
        alert(`Error ${statusCode}: ${message}`);
    } else if (error.request) {
        alert('No response received from the server.');
    } else {
        alert(`Error: ${error.message}`);
    }
    console.error('Error:', error);
}

function isNotNullAndUndefined(value) {
    return value !== null && value !== undefined;
}

function timeAgo(createdAt) {
    const now = new Date();
    const createdTime = new Date(createdAt);
    const diffMs = now - createdTime;
    const diffMins = Math.floor(diffMs / 60000); // 밀리초를 분으로 변환

    if (diffMins < 1){
        return `방금`;
    }
    else if (diffMins < 60) {
        return `${diffMins}분 전`;
    } else {
        const diffHours = Math.floor(diffMins / 60);
        return `${diffHours}시간 전`;
    }
}