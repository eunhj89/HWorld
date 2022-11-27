package com.uno.hworld.controller;

import com.uno.hworld.common.JwtTokenProvider;
import com.uno.hworld.common.Message;
import com.uno.hworld.common.MessageRoom;
import com.uno.hworld.repository.MsgRoomRepository;
import com.uno.hworld.service.MsgService;
import com.uno.hworld.service.RedisPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MsgController {

    // private final SimpMessageSendingOperations sendingOperations; //this is for STOMP.
    // private final RedisPublisher redisPublisher;
    // private final MsgRoomRepository msgRoomRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final JwtTokenProvider jwtTokenProvider;
    private final ChannelTopic channelTopic;


    @MessageMapping("/chat/message")
    public void message(Message message, @Header("token") String token) {
        String id = jwtTokenProvider.getUserNameFromJwt(token);
        message.setSender(id);

        if (Message.MessageType.ENTER.equals(message.getMessageType())) {
            //msgRoomRepository.enterMsgRoom(message.getRoomId());
            message.setSender("[알림]");
            message.setMessage(id + "이 입장했습니다.");
        }
        //redisPublisher.publish(msgRoomRepository.getTopic(message.getRoomId()), message);
        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
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
