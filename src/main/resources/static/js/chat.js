(function() {
    let sock = new SockJS("/ws-chat");
    let ws = Stomp.over(sock);
    let roomId = 'all_chat_roomId';
    let roomName = 'all_chat';
    let token;

    let playerChatEnterInput = document.querySelector("#player-chat-enter");
    let playerChatEnterButton = document.querySelector("#player-chat-enter-b");
    let playerChatShowList = document.querySelector("#player-chat-show-ul");

    playerChatEnterInput.addEventListener("keypress", (e) => {
        if (e.key == 'Enter') {
            ws.send("/pub/chat/message", {"token": token}, JSON.stringify({messageType:'TALK', roomId: roomId, message:e.target.value}));
            e.target.value = '';
        }
    });
    playerChatEnterButton.addEventListener("click", (e) => {
        ws.send("/pub/chat/message", {"token": token}, JSON.stringify({messageType:'TALK', roomId: roomId, message:playerChatEnterInput.value}));
        playerChatEnterInput.value = '';
    });

    function create() {
        axios.get('/chat/user').then(response => {
            token = response.data.token;
            ws.connect({"token": token}, function(frame) {
                ws.subscribe("/sub/chat/room/"+roomId, function(message) {
                    let rec = JSON.parse(message.body);
                    recvMessage(rec);
                });
            }, function(error) {
                alert("서버 연결에 실패 하였습니다. 다시 접속해 주십시요.");
                location.href="/chat/room";
            });
        });
    };

    let recvMessage = function(rec) {
        const pLi = playerChatShowList.getElementsByTagName('li');
        if (pLi.length > 6) {
            pLi[0].remove();
        }
        const li = document.createElement("li");
        li.setAttribute('class', 'player-chat-show-li');
        li.appendChild(document.createTextNode(rec.sender + ' : ' + rec.message));
        playerChatShowList.appendChild(li);

        const allCharacterNames = document.getElementsByClassName("Character_name");
        for (let characterName of allCharacterNames) {
            if (characterName.innerHTML == rec.sender) {
                characterName.parentElement.nextElementSibling.children[0].innerHTML = rec.sender + ' : ' + rec.message;
                characterName.parentElement.nextElementSibling.style.display = "flex";
                setTimeout(function() {
                    characterName.parentElement.nextElementSibling.children[0].innerHTML = '';
                    characterName.parentElement.nextElementSibling.style.display = "none";
                }, 5000);
            }
        }
    };

    create();
})();