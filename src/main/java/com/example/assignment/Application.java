package com.example.assignment;


import com.example.assignment.utils.ChatType;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter chatType (single/multi): ");
        String chatTypeInput = sc.nextLine();

        ChatType chatType = ChatType.byValue(chatTypeInput);

        if (chatType == ChatType.SINGLE_PROCESS) {
            buildSingleProcess(sc);
        } else {
            buildMultiProcess(sc);
        }
    }

    private static void buildSingleProcess(Scanner sc) {
        System.out.println("Type your message: ");
        String message = sc.nextLine();

        ChatBuilder chatBuilder = ChatBuilder.BUILDER(ChatType.SINGLE_PROCESS)
                .initiatorUsername("Saeid")
                .secondPlayerUsername("Sara")
                .build();

        chatBuilder.getInitiator().initConversation(message, chatBuilder.secondPlayer().getUsername());
    }

    private static void buildMultiProcess(Scanner sc) {
        String message = "";
        int port;
        boolean isPlayerInitiator;
        System.out.println("Please enter your username: ");
        String username = sc.nextLine();

        System.out.println("Are you initiator (y/n)?");
        String input = sc.nextLine();
        if (input.equals("y")) {
            System.out.println("Please type the second player port: ");
            port = sc.nextInt();
            System.out.println("Type your message: ");
            message = sc.next();
            isPlayerInitiator = true;
        } else {
            System.out.println("Please enter port number to receive messages:");
            port = sc.nextInt();
            isPlayerInitiator = false;
        }
        ChatBuilder chatBuilder = ChatBuilder.BUILDER(ChatType.MULTI_PROCESS)
                .initiatorUsername(username)
                .port(port)
                .isPlayerInitiator(isPlayerInitiator)
                .build();
        if (isPlayerInitiator)
            chatBuilder.getInitiator().initConversation(message, chatBuilder.getInitiator().getUsername());
    }

}
