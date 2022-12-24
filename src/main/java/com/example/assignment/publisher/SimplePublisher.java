package com.example.assignment.publisher;


import com.example.assignment.Message;
import com.example.assignment.subscribe.Subscriber;

public class SimplePublisher implements Publisher {

    @Override
    public void notify(Message message, Subscriber receiver) {
        receiver.onReceived(message);
    }
}
