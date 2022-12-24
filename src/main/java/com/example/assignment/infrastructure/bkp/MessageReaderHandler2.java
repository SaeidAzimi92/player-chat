//package com.example.assignment.infrastructure.bkp;
//
//import com.example.assignment.MessageListener;
//import com.example.assignment.utils.Constant;
//
//import java.io.IOException;
//import java.net.DatagramPacket;
//import java.net.InetAddress;
//import java.net.MulticastSocket;
//import java.nio.charset.StandardCharsets;
//
//public class MessageReaderHandler2 extends Thread {
//    private final MulticastSocket socket;
//    private final InetAddress group;
//    private final MessageListener messageListener;
//    private static final int MAX_LEN = 1000;
//    private volatile boolean finished = false;
//
//
//
//
//
//
//    MessageReaderHandler2(MulticastSocket socket, InetAddress group, MessageListener messageListener) {
//        this.socket = socket;
//        this.group = group;
//        this.messageListener = messageListener;
//    }
//
//    @Override
//    public void run() {
//        while (!finished) {
//            byte[] buffer = new byte[MAX_LEN];
//            DatagramPacket datagram = new
//                    DatagramPacket(buffer, buffer.length, group, Constant.PORT);
//            String message;
//            try {
//                socket.receive(datagram);
//                message = new
//                        String(buffer, 0, datagram.getLength(), StandardCharsets.UTF_8);
////                if (!message.startsWith(name))
//                messageListener.onReceivedMessage(message);
//                    System.out.println(message);
//            } catch (IOException e) {
////                System.out.println("Socket closed!");
//            }
//        }
//    }
//
//    public void finishedReading(boolean finished) {
//        this.finished = finished;
//    }
//}