package com.example.assignment.infrastructure;

import com.example.assignment.Message;
import com.example.assignment.publisher.Publisher;
import com.example.assignment.subscribe.Subscriber;
import com.example.assignment.utils.Constant;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseChatDispatcher implements ChatDispatcher{
    protected final Logger logger = Logger.getLogger(getClass().getName());
    protected Publisher publisher;
    protected final Map<String, Subscriber> subscribers = new HashMap<>();


    protected boolean conversationHasBeenFinished(Message message) {
        var subscriber = subscribers.get(message.getOwner());
        if (subscriber.getMessageCounter() - 1 > Constant.MAX_MESSAGE_COUNTER) {
            logger.log(Level.INFO, "Convesation has ended!");
            return true;
        }
        return false;
    }
}
