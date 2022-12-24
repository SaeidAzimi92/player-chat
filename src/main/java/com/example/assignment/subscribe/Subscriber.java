package com.example.assignment.subscribe;

import com.example.assignment.Message;

public interface Subscriber {

    String getUsername();

    int getMessageCounter();

    void onReceived(Message message);

}
