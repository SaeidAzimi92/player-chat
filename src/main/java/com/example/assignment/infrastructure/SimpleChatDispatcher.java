package com.example.assignment.infrastructure;

import com.example.assignment.Message;
import com.example.assignment.subscribe.Subscriber;
import com.example.assignment.publisher.SimplePublisher;

public class SimpleChatDispatcher extends BaseChatDispatcher {


    public SimpleChatDispatcher() {
        this.publisher = new SimplePublisher();
    }

    @Override
    public void registerSubscriber(String username, Subscriber subscriber) {
        if (subscribers.containsKey(username)) {
            throw new IllegalArgumentException("Player has been already registered!");
        }
        subscribers.put(username, subscriber);
    }

    @Override
    public void dispatch(Message message) {
        if (conversationHasBeenFinished(message)) return;
        var subscriber = subscribers.get(message.getReceiver());
        publisher.notify(message, subscriber);
    }
}
