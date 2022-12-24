package com.example.assignment.infrastructure;

import com.example.assignment.Message;
import com.example.assignment.MessageListener;
import com.example.assignment.publisher.SocketPublisher;
import com.example.assignment.subscribe.Subscriber;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class SocketChatDispatcher extends BaseChatDispatcher implements MessageListener {
    private Socket socket;
    private volatile boolean isFinished = false;

    public SocketChatDispatcher(int port, boolean isPlayerInitiator) {
        try {
            if (isPlayerInitiator) {
                socket = SocketHelper.createSocketClient(port);
            } else {
                var socketServer = SocketHelper.startServerSocket(port);
                socket = socketServer.accept();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                while (!isFinished) {
                MessageReaderHandler mtch = new MessageReaderHandler(bufferedReader, this);
                mtch.start();
//                }
            }

            publisher = new SocketPublisher(socket);

        } catch (
                SocketException se) {
            System.out.println("Error creating socket");
            se.printStackTrace();
        } catch (
                IOException ie) {
            System.out.println("Error reading/writing from/to socket");
        }
    }

    private void terminateConversation() {
        try {
            isFinished = true;
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        if (conversationHasBeenFinished(message)) {
            terminateConversation();
            return;
        }
        var subscriber = subscribers.get(message.getReceiver());
        publisher.notify(message, subscriber);
    }


    @Override
    public void onReceivedMessage(String messageString) {
        if (messageString.equals("finished")) {
            isFinished = true;
            return;
        }

        var message = Message.parseMessage(messageString);
        if (message == null) return;
        var subscriber = getCurrentSubscriber();
        if (message.getReceiver().equals(message.getOwner()) && subscriber.getMessageCounter() == 1) {
            return;
        }
        subscriber.onReceived(message);
    }

    private Subscriber getCurrentSubscriber() {
        return subscribers.entrySet().iterator().next().getValue();
    }
}
