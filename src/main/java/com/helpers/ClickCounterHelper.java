package com.helpers;

import com.controllers.ClickCounterController;
import com.models.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;

public class ClickCounterHelper {

    private static Logger logger = LoggerFactory.getLogger(ClickCounterHelper.class);

    public void broadcastCounter() {
        ClickCounterController.sessions.forEach(s -> {
            try {
                s.sendMessage(new TextMessage(Counter.getCounter().toString()));
            } catch (IOException e) {
                logger.warn("Message was not sent to session with id: [{}]", s.getId());
            }
        });
    }
}
