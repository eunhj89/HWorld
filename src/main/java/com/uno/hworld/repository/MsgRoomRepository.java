package com.uno.hworld.repository;

import com.uno.hworld.common.MsgRoom;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class MsgRoomRepository {

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

    public MsgRoom findByRoomId(String roomId) {
        return msgRoomMap.get(roomId);
    }

    public MsgRoom createMsgRoom(String name) {
        MsgRoom room = MsgRoom.builder().id(UUID.randomUUID().toString()).roomId(name).build();
        msgRoomMap.put(room.getRoomId(), room);
        return room;
    }
}
