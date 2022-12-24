package com.example.assignment.infrastructure;

import com.example.assignment.MessageListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MessageReaderHandler extends Thread {
    private final MessageListener messageListener;
    private static final int MAX_LEN = 1000;
    private volatile boolean finished = false;
    private final BufferedReader bufferedReader;

    public MessageReaderHandler(BufferedReader bufferedReader, MessageListener messageListener) {
        this.bufferedReader = bufferedReader;
        this.messageListener = messageListener;
    }


    @Override
    public void run() {
        try {
            String message = bufferedReader.readLine();
            messageListener.onReceivedMessage(message);
        } catch (IOException e) {
            System.out.println("Socket closed!");
            messageListener.onReceivedMessage("finished");
        }
    }

}