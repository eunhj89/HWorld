package com.uno.hworld.common;
/**
 * This handler is used for Websocket
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uno.hworld.service.MsgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    /**
     * This handler is used for Websocket.
     * It is not used anymore.
     */

    private final MsgService msgService;
    private final ObjectMapper objectMapper;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        String payload = textMessage.getPayload();
        log.info("payload : {}" , payload);

        Message msg = objectMapper.readValue(payload, Message.class);
        MessageRoom room = msgService.findById(msg.getRoomId());
        room.handleActions(session, msg, msgService);
    }
}
