package com.example.assignment;


public class Message {
    private final String content;
    private final String receiver;
    private final String owner;

    public static Message builder(String message, String receiver, String owner) {
        return new Message(message, receiver, owner);
    }

    private Message(String content, String receiver, String owner) {
        this.content = content;
        this.receiver = receiver;
        this.owner = owner;
    }

    public String getContent() {
        return content;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "{" +
                "content:" + getContent() + "," +
                "receiver:" + getReceiver() + "," +
                "owner:" + getOwner() +
                "}";
    }

    public static Message parseMessage(String messageString) {
        if (messageString == null || messageString.isEmpty()) {
            return null;
        }
        String message = messageString.substring(1, messageString.length() - 1);
        String[] parameters = message.split(",");
        String content = parameters[0].split(":")[1];
        String receiver = parameters[1].split(":")[1];
        String owner = parameters[2].split(":")[1];

        return builder(content, receiver, owner);
    }
}
