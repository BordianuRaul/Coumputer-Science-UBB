package com.example.manager;

import com.example.domain.User;
import com.example.domain.UserRequest;

import java.io.IOException;
import java.net.Socket;

import static com.example.utils.MessageUtils.BYE;
import static com.example.utils.MessageUtils.HELLO;
import static com.example.server.Server.SERVER_ADDRESS;

public class Manager {
    private final User user;

    public Manager(User user) {
        this.user = user;
    }

    public void connectToUser(int userPort) throws IOException {
        try {
            user.addSocket(new Socket(SERVER_ADDRESS, userPort));
            user.initializeStreams();
        } catch (IOException e) {
            throw new IOException("Error occured, cannot connect to the user.");
        }
    }

    public void closeConnection() throws IOException {
        try {
            if (user.getSockets() != null) {
                user.closeMainSocket();
            }
        } catch (IOException e) {
            throw new IOException("Error occured, cannot close the connection.");
        }
    }

    public void sendMessage(String message) {
        user.getMainWriter().println(new UserRequest(user.getUsername(), message).toJson());
    }

    public UserRequest receiveMessage() throws Exception {
        String jsonMessage = user.getReader().readLine();
        return UserRequest.fromJson(jsonMessage);
    }

    public void listenForMessages() {
        while (user.getNumberOfConnections() > 0) {
            try {
                if (user.getReader().ready()) {
                    final UserRequest userMessage = receiveMessage();
                    if (userMessage.getContent().contains(HELLO)) {
                        System.out.println(userMessage.getSender() + " connected to you");
                        user.addUsersConnected(userMessage.getSender());
                    } else if (userMessage.getContent().contains(BYE)) {
                        closeConnection();
                        System.out.println("User Disconnected");
                    } else {
                        System.out.println("<" + userMessage.getSender() + ">" + " " + userMessage.getContent());
                    }
                }
            } catch (final Exception e) {
                throw new RuntimeException("Error occurred while receiving the message." + e.getMessage());
            }
        }
    }
}
