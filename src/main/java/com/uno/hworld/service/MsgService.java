package com.uno.hworld.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uno.hworld.common.MessageRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MsgService {

    /**
     * This handler is used for Websocket.
     * It is not used anymore.
     */

    private final ObjectMapper objectMapper;
    private Map<String, MessageRoom> msgRooms;

    @PostConstruct
    private void init() {
        msgRooms = new LinkedHashMap<>();
    }

    public List<MessageRoom> findAllRoom() {
        return new ArrayList<>(msgRooms.values());
    }

    public MessageRoom findById(String roomId) {
        return msgRooms.get(roomId);
    }

    public MessageRoom createRoom(String name) {
        String roomId = name;
        MessageRoom msgRoom = MessageRoom.builder().roomId(roomId).build();
        msgRooms.put(roomId, msgRoom);
        return msgRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

}
