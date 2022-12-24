//package com.example.assignment.infrastructure.bkp;
//
//import com.example.assignment.Message;
//import com.example.assignment.MessageListener;
//import com.example.assignment.infrastructure.BaseChatDispatcher;
//import com.example.assignment.publisher.SocketPublisher;
//import com.example.assignment.subscribe.Subscriber;
//import com.example.assignment.utils.Constant;
//
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.MulticastSocket;
//import java.net.SocketException;
//
//public class SocketChatDispatcher2 extends BaseChatDispatcher implements MessageListener {
//    private MulticastSocket socket;
//    private InetAddress group;
//
//    public SocketChatDispatcher2() {
//        try {
//            socket = createSocket();
//            group = InetAddress.getByName(Constant.IP);
////            this.publisher = new SocketPublisher(socket, group);
//            var messageReaderHandler = new MessageReaderHandler2(socket, group, this);
//            // Spawn a thread for reading messages
//            messageReaderHandler.start();
//        } catch (
//                SocketException se) {
//            System.out.println("Error creating socket");
//            se.printStackTrace();
//        } catch (
//                IOException ie) {
//            System.out.println("Error reading/writing from/to socket");
//        }
//    }
//
//    private void terminateConversation() {
//        try {
//            socket.leaveGroup(group);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            socket.close();
//        }
//    }
//
//    private MulticastSocket createSocket() throws IOException {
//        var socket = new MulticastSocket(Constant.PORT);
//        // Since we are deploying
//        socket.setTimeToLive(0);
//        //this on localhost only (For a subnet set it as 1)
//        socket.setLoopbackMode(false);
//
//        return socket;
//    }
//
//
//    @Override
//    public void registerSubscriber(String username, Subscriber subscriber) {
//        if (subscribers.containsKey(username)) {
//            throw new IllegalArgumentException("Player has been already registered!");
//        }
//        subscribers.put(username, subscriber);
//        try {
//            socket.joinGroup(group);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Override
//    public void dispatch(Message message) {
//        if (conversationHasBeenFinished(message)) {
//            terminateConversation();
//            return;
//        }
//        var subscriber = subscribers.get(message.getReceiver());
//        publisher.notify(message, subscriber);
//    }
//
//
//    @Override
//    public void onReceivedMessage(String messageString) {
//        var message = Message.parseMessage(messageString);
//        if (message == null) return;
//        var subscriber = subscribers.get(message.getReceiver());
//        if (message.getReceiver().equals(message.getOwner()) && subscriber.getMessageCounter() == 2) {
//            return;
//        }
//        subscriber.onReceived(message);
//    }
//}
