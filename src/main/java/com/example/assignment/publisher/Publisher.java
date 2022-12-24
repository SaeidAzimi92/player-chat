package com.example.assignment.publisher;


import com.example.assignment.Message;
import com.example.assignment.subscribe.Subscriber;

/**
 * publish a message to be received by subscriber through different ways
 */
public interface Publisher {

    void notify(Message message, Subscriber receiver);

}
