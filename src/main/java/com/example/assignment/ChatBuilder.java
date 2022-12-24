package com.example.assignment;

import com.example.assignment.infrastructure.ChatDispatcher;
import com.example.assignment.infrastructure.SimpleChatDispatcher;
import com.example.assignment.infrastructure.SocketChatDispatcher;
import com.example.assignment.utils.ChatType;

public class ChatBuilder {
    private final Player initiator;
    private final Player secondPlayer;
    private final ChatDispatcher dispatcher;

    private ChatBuilder(Player initiator, Player secondPlayer, ChatDispatcher dispatcher) {
        this.initiator = initiator;
        this.secondPlayer = secondPlayer;
        this.dispatcher = dispatcher;
    }

    public Player getInitiator() {
        return initiator;
    }

    public Player secondPlayer() {
        return secondPlayer;
    }

    public ChatDispatcher getDispatcher() {
        return dispatcher;
    }

    public static BUILDER BUILDER(ChatType type) {
        return new BUILDER(type);
    }

    public static class BUILDER {
        private String initiatorUsername = "Player1";
        private String secondPlayerUsername = "Player2";
        private final ChatType type;
        private int port;
        private boolean isPlayerInitiator;

        public BUILDER(ChatType type) {
            this.type = type;
        }

        public BUILDER initiatorUsername(String initiatorUsername) {
            this.initiatorUsername = initiatorUsername;
            return this;
        }

        public BUILDER secondPlayerUsername(String secondPlayerUsername) {
            this.secondPlayerUsername = secondPlayerUsername;
            return this;
        }

        public BUILDER port(int port) {
            this.port = port;
            return this;
        }
        public BUILDER isPlayerInitiator(boolean isPlayerInitiator) {
            this.isPlayerInitiator = isPlayerInitiator;
            return this;
        }

        public ChatBuilder build() {
            if (type == ChatType.MULTI_PROCESS) {
                return buildMultiProcessChat();
            }

            return buildSingleProcessChat();
        }

        private ChatBuilder buildMultiProcessChat() {
            ChatDispatcher dispatcher = new SocketChatDispatcher(port, isPlayerInitiator);
            Player initiator = new Player(dispatcher, this.initiatorUsername);
            dispatcher.registerSubscriber(initiator.getUsername(), initiator);
            return new ChatBuilder(initiator, null, dispatcher);
        }

        private ChatBuilder buildSingleProcessChat() {
            ChatDispatcher dispatcher = new SimpleChatDispatcher();
            Player initiator = new Player(dispatcher, this.initiatorUsername);
            Player secondPlayer = new Player(dispatcher, secondPlayerUsername);

            dispatcher.registerSubscriber(initiator.getUsername(), initiator);
            dispatcher.registerSubscriber(secondPlayer.getUsername(), secondPlayer);

            return new ChatBuilder(initiator, secondPlayer, dispatcher);
        }
    }
}
