let stompClient = null;

function connect() {
    const token = document.getElementById('tokenInput').value;
    if (!token) {
        document.getElementById('output').insertAdjacentHTML('afterbegin', '<p>Please enter a token.</p>');
        return;
    }

    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({ Authorization: `Bearer ${token}` }, (frame) => {
        console.log('Connected: ' + frame);
        document.getElementById('output').insertAdjacentHTML('afterbegin', '<p>Connected: ' + frame + '</p>');

        // Подписка на топик
        stompClient.subscribe('/post', (message) => {
            console.log('Received: ' + message.body);
            document.getElementById('output').insertAdjacentHTML('afterbegin', '<p>Received: ' + message.body + '</p>');
        });
        stompClient.subscribe('/user/post/feed/posted', (message) => {
            console.log('Received: ' + message.body);
            document.getElementById('output').insertAdjacentHTML('afterbegin', '<p>Received: ' + message.body + '</p>');
        });
    }, (error) => {
        console.error('Connection error: ', error);
        document.getElementById('output').insertAdjacentHTML('afterbegin', '<p>Connection error: ' + error + '</p>');
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect(() => {
            console.log('Disconnected');
            document.getElementById('output').insertAdjacentHTML('afterbegin', '<p>Disconnected</p>');
        });
        stompClient = null;
    } else {
        document.getElementById('output').insertAdjacentHTML('afterbegin', '<p>Not connected. Please connect first.</p>');
    }
}

function clearOutput() {
    document.getElementById('output').innerHTML = '';
}

document.addEventListener('DOMContentLoaded', (event) => {
    document.getElementById('connect').onclick = connect;
    document.getElementById('send').onclick = sendMessage;
    document.getElementById('disconnect').onclick = disconnect;
    document.getElementById('clearOutput').onclick = clearOutput;

    // Initialize output area with empty content
    document.getElementById('output').innerHTML = '<p style="visibility: hidden;">.</p>';
});
