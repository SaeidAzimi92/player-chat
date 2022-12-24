package com.example.assignment.infrastructure;

import com.example.assignment.utils.Constant;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketHelper {
    private static final Logger logger = Logger.getLogger(SocketHelper.class.getName());

    public static ServerSocket startServerSocket(int port) throws IOException {
        return new ServerSocket(port);
    }


    public static Socket createSocketClient(int port) throws IOException {
        try {
            return new Socket(Constant.IP, port);
        } catch (IOException e) {
            throw new IOException(
                    String.format("Could not connect to this address: %s:%s, please try again later", Constant.IP, port)
                    , e);
        }
    }

    public static void sendMessage(Socket clientSocket, byte[] input) throws IOException {
        BufferedOutputStream bufferedOutputStream =
                new BufferedOutputStream(clientSocket.getOutputStream());
        logger.log(Level.INFO, String.format("Send message to %s:%s : %s", Constant.IP, clientSocket.getPort(), new String(input)));
        bufferedOutputStream.write(input);
        bufferedOutputStream.flush();
    }

    public static String receiveData(Socket clientSocket) throws IOException {
        InputStream socketInputStream = clientSocket.getInputStream();
        String message = "";
        if (socketInputStream != null) {
            byte[] inputStream = socketInputStream.readAllBytes();
            logger.info(String.format("Received Message from ISC: %s", new String(inputStream)));
            if (inputStream.length == 0)
                throw new IOException(String.format("Not responding: %s", Constant.IP));
            message = new String(inputStream, StandardCharsets.UTF_8);
        }
        return message;
    }

}
