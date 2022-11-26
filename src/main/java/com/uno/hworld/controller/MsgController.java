package com.uno.hworld.controller;

import com.uno.hworld.common.Message;
import com.uno.hworld.common.MessageRoom;
import com.uno.hworld.repository.MsgRoomRepository;
import com.uno.hworld.service.MsgService;
import com.uno.hworld.service.RedisPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MsgController {

    // private final SimpMessageSendingOperations sendingOperations; //this is for STOMP.
    private final RedisPublisher redisPublisher;
    private final MsgRoomRepository msgRoomRepository;

    @MessageMapping("/chat/message")
    public void message(Message message) {
        if (Message.MessageType.ENTER.equals(message.getMessageType())) {
            msgRoomRepository.enterMsgRoom(message.getRoomId());
            message.setMessage(message.getSender() + "이 입장했습니다.");
        }
        redisPublisher.publish(msgRoomRepository.getTopic(message.getRoomId()), message);
    }

    /**
     * This source below is used for Websocket.
     * It is not used anymore.
     */

    private final MsgService msgService;

    @PostMapping("/chat")
    public MessageRoom createRoom(@RequestParam String name) {
        return msgService.createRoom(name);
    }

    @GetMapping("/chat")
    public List<MessageRoom> findAllRoom() {
        return msgService.findAllRoom();
    }


}
