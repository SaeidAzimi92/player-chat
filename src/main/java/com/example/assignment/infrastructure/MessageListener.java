package com.example.assignment.infrastructure;

public interface MessageListener {
    void onMessage(String message);

    void onFinish();
}
