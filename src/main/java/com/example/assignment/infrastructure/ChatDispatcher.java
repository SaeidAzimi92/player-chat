package com.example.assignment.infrastructure;

import com.example.assignment.Message;
import com.example.assignment.subscribe.Subscriber;

public interface ChatDispatcher {

    void registerSubscriber(String username, Subscriber subscriber);

    void dispatch(Message message);
}
