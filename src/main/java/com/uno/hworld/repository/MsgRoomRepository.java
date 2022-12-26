package com.uno.hworld.repository;

import com.uno.hworld.common.MsgRoom;
import com.uno.hworld.service.RedisSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@RequiredArgsConstructor
@Repository
public class MsgRoomRepository {

    // private final RedisMessageListenerContainer redisMessageListenerContainer;

    // private final RedisSubscriber redisSubscriber;

    private static final String MSG_ROOMS = "MSG_ROOM";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, MsgRoom> opsHashMsgRoom;
    // private Map<String, ChannelTopic> topics;

    @PostConstruct
    private void init() {
        opsHashMsgRoom = redisTemplate.opsForHash();
        MsgRoom msgRoom = MsgRoom.create("all_chat");
        msgRoom.setRoomId("all_chat_roomId");
        opsHashMsgRoom.put(MSG_ROOMS, msgRoom.getRoomId(), msgRoom);
        // topics = new HashMap<>();
    }

    public List<MsgRoom> findAllRoom() {
        return opsHashMsgRoom.values(MSG_ROOMS);
    }

    public MsgRoom findRoomById(String id) {
        return opsHashMsgRoom.get(MSG_ROOMS, id);
    }

    public MsgRoom createMsgRoom(String roomName) {
        MsgRoom msgRoom = MsgRoom.create(roomName);
        opsHashMsgRoom.put(MSG_ROOMS, msgRoom.getRoomId(), msgRoom);
        return msgRoom;
    }
    /*
    public void enterMsgRoom(String roomId) {
        ChannelTopic topic = topics.get(roomId);
        if (topic == null) {
            topic = new ChannelTopic(roomId);
            redisMessageListenerContainer.addMessageListener(redisSubscriber, topic);
            topics.put(roomId, topic);
        }
    }

    public ChannelTopic getTopic(String roomId) {
        return topics.get(roomId);
    }
    */

    /**
     * This handler is used for STOMP.
     * It is not used anymore.
     */

    /*
    private Map<String, MsgRoom> msgRoomMap;

    @PostConstruct
    private void init() {
        msgRoomMap = new LinkedHashMap<>();
    }

    public List<MsgRoom> findAllRoom() {
        List<MsgRoom> msgRooms = new ArrayList<>(msgRoomMap.values());
        Collections.reverse(msgRooms);
        return msgRooms;
    }

    public MsgRoom findRoomById(String roomId) {
        return msgRoomMap.get(roomId);
    }

    public MsgRoom createMsgRoom(String roomName) {
        MsgRoom room = MsgRoom.builder().roomId(UUID.randomUUID().toString()).roomName(roomName).build();
        msgRoomMap.put(room.getRoomId(), room);
        return room;
    }
     */
}
