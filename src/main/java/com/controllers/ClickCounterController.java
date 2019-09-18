package com.controllers;

import com.helpers.ClickCounterHelper;
import com.models.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ClickCounterController extends AbstractWebSocketHandler {

    private static Logger logger = LoggerFactory.getLogger(ClickCounterController.class);
    private static Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        try {
            session.sendMessage(new TextMessage(Counter.getCounter().toString()));
        } catch (IOException e) {
            logger.warn("Message was not sent to session with id: [{}]", session.getId());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        Counter.incrementCounter();
        ClickCounterHelper.broadcastCounter();
    }
}