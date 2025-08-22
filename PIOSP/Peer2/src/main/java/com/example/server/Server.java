package com.example.server;

import com.example.domain.Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private final Map<String, Integer> users = new HashMap<>();
    public static final int SERVER_PORT = 5000;
    public static final String SERVER_ADDRESS = "localhost";

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Waiting for connection...");
            while (true) {
                try (Socket socket = serverSocket.accept();
                     final var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     final var writer = new PrintWriter(socket.getOutputStream(), true)) {
                        System.out.println("New command: ");
                        String jsonMessage = reader.readLine();
                        System.out.println(jsonMessage);
                        ServerRequest userMessage = ServerRequest.fromJson(jsonMessage);
                        Commands command = userMessage.getCommand();
                        switch (command) {
                            case LOGIN -> loginUser(userMessage.getContent());
                            case ALL -> getAllUsers(writer);
                            case LOGOUT -> logoutUser(userMessage.getContent());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void logoutUser(String content) {
        final String[] argc = content.split(" ");
        final String username = argc[ 0 ];
        users.remove(username);
    }

    private void getAllUsers(PrintWriter writer) {
        writer.println(String.join(",", users.entrySet().stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .toArray(String[]::new)));
    }

    private void loginUser(String content) {
        final String[] argc = content.split(" ");
        final String username = argc[ 0 ];
        final int port = Integer.parseInt(argc[ 1 ]);
        users.put(username, port);
    }
}
