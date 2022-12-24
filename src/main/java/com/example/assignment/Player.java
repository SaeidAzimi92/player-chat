package com.example.assignment;

import com.example.assignment.infrastructure.ChatDispatcher;
import com.example.assignment.subscribe.Subscriber;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Player implements Subscriber {
    private final Logger logger = Logger.getLogger(getClass().getName());

    private int messageCounter;
    private final String username;
    private final ChatDispatcher dispatcher;

    public Player(ChatDispatcher dispatcher, String username) {
        this.dispatcher = dispatcher;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public int getMessageCounter() {
        return this.messageCounter;
    }

    public void initConversation(String message, String receiver) {
        messageCounter += 1;
        dispatcher.dispatch(Message.builder(message, receiver, username));
    }

    @Override
    public void onReceived(Message message) {
        logger.log(Level.INFO, String.format("%s received message: %s ", username, message.getContent()));
        replyMessage(message);
    }

    private void replyMessage(Message message) {
        String content = message.getContent().concat(" ").concat(String.valueOf(messageCounter));
        messageCounter += 1;
        dispatcher.dispatch(Message.builder(content, message.getOwner(), getUsername()));
    }
}
