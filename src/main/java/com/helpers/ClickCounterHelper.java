package com.helpers;

import com.controllers.ClickCounterController;
import com.models.Counter;
import com.models.CounterResponse;
import com.models.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Set;

/**
 * ClickCounter Helper
 * Helper methods for ClickCounter Controller
 */
public class ClickCounterHelper {

    private static Logger logger = LoggerFactory.getLogger(ClickCounterHelper.class);

    /**
     * Broadcast Counter Helper Method
     * Broadcasts the current Counter value to all connected WebSocket sessions
     * @param sessions Set of all connected sessions
     * @param counter Current counter DTO
     */
    public static void broadcastCounter(Set<WebSocketSession> sessions, Counter counter) {
        sessions.forEach(s -> {
            try {
                CounterResponse response = new CounterResponse(ResponseStatus.OK.name(), counter.getCounter());
                s.sendMessage(new TextMessage(response.toString()));
            } catch (IOException e) {
                logger.warn("Message was not sent to session with id [{}] due to: [{}]", s.getId(), e.getMessage());
            }
        });
    }
}
