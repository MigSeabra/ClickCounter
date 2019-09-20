package com.configurations;

import com.controllers.ClickCounterController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocket Registration Class
 * Registers WebSocket handler and enables it
 */
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    /**
     * WebSocket Registration Method
     * Registers ClickCounterController
     * @param registry Spring framework parameter that allows to add handlers
     */
    @Override
    public void	registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ClickCounterController(), "/incrementCounter");

    }
}
