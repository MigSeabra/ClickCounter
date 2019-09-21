package com.controllers;

import com.helpers.ClickCounterHelper;
import com.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClickCounter Controller
 * Handler to process WebSocket messages for increment counter events
 */
public class ClickCounterController extends AbstractWebSocketHandler {

    private static Logger logger = LoggerFactory.getLogger(ClickCounterController.class);
    private Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
    private Counter counter = new Counter();

    /**
     * After Connections Established Handler
     * Handles after WebSocket connection established event
     * @param session Current WebSocket session object
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        logger.info("Connection established with session ID: [{}]", session.getId());
        sessions.add(session);
        try {
            CounterResponse response = new CounterResponse(ResponseStatus.OK.name(), counter.getCounter());
            session.sendMessage(new TextMessage(response.toString()));
        } catch (IOException e) {
            logger.warn("Message was not sent to session with id: [{}] due to: [{}]", session.getId(), e.getMessage());
        }
    }

    /**
     * After Connections Closed Handler
     * Handles after WebSocket connection closed event
     * @param session Closed WebSocket session object
     * @param status Closed connection status
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        logger.info("Connection closed from session ID [{}], with Status Code [{}] and Reason [{}]",
                session.getId(),
                status.getCode(),
                status.getReason());
        sessions.remove(session);
    }

    /**
     * Messages Received Handler
     * Handles messages received from WebSocket
     * @param session Connected WebSocket session object
     * @param message Received WebSocket message
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        CounterOperationRequest request = CounterOperationRequest.toObject(message.getPayload());
        if (request != null && CounterOperations.INCREMENT == request.getOperation()) {
            logger.info("Increment message received from session ID: [{}]", session.getId());
            counter.incrementCounter();
            ClickCounterHelper.broadcastCounter(sessions, counter);
        }
    }

    /**
     * Errors Handler
     * Handles errors from WebSocket
     * @param session Connected WebSocket session object
     * @param exception Error exception object
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        logger.warn("An error occurred in session ID [{}]: [{}]", session.getId(), exception.getMessage());
    }
}