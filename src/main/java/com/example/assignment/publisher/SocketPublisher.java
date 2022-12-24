package com.example.assignment.publisher;

import com.example.assignment.Message;
import com.example.assignment.infrastructure.SocketHelper;
import com.example.assignment.subscribe.Subscriber;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * publish messages through socket
 */
public class SocketPublisher implements Publisher {
    private final Socket socket;

    public SocketPublisher(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void notify(Message message, Subscriber subscriber) {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println(message.toString());

//            SocketHelper.sendMessage(socket, message.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
