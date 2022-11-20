package com.uno.hworld.controller;

import com.uno.hworld.common.Message;
import com.uno.hworld.common.MessageRoom;
import com.uno.hworld.service.MsgService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MsgController {

    private final MsgService msgService;

    private final SimpMessageSendingOperations sendingOperations;

    @PostMapping("/chat")
    public MessageRoom createRoom(@RequestParam String name) {
        return msgService.createRoom(name);
    }

    @GetMapping("/chat")
    public List<MessageRoom> findAllRoom() {
        return msgService.findAllRoom();
    }

    @MessageMapping("/chat/message")
    public void message(Message message) {
        if (Message.MessageType.ENTER.equals(message.getMessageType())) {
            message.setMessage(message.getSender() + "이 입장했습니다.");
        }
        sendingOperations.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

}
