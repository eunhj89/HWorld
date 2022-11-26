package com.uno.hworld.common;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MsgRoom implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;

    @Id
    private String roomId;

    @Column(nullable = false)
    private String roomName;

    public static MsgRoom create(String roomName) {
        MsgRoom msgRoom = new MsgRoom();
        msgRoom.roomId = UUID.randomUUID().toString();
        msgRoom.roomName = roomName;
        return msgRoom;
    }

}
