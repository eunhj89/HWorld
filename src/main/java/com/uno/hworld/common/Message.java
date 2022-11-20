package com.uno.hworld.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Message {
    public enum MessageType {
        ENTER, TALK
    }

    private MessageType messageType;
    private String roomId;
    private String sender;
    private String message;

}
