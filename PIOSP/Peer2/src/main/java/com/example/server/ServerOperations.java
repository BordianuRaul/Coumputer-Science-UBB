package com.example.server;

import com.example.domain.Commands;
import com.example.domain.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static com.example.server.Server.SERVER_ADDRESS;
import static com.example.server.Server.SERVER_PORT;

public class ServerOperations {
    private final User user;
    private Socket serverSocket;
    private PrintWriter serverWriter;
    private BufferedReader serverReader;

    public ServerOperations(User user) {
        this.user = user;
    }

    public void connectToServer() throws IOException {
        openServerSocket();
        final String content = user.getUsername() + " " + user.getServerSocket().getLocalPort();
        sendRequestToTheServer(Commands.LOGIN, content);
        serverSocket.close();
    }

    public void disconnectFromServer() throws IOException {
        openServerSocket();
        sendRequestToTheServer(Commands.LOGOUT, user.getUsername());
        serverSocket.close();
    }

    public boolean connectToUserFromServer(String receiver) throws IOException {
        System.out.println("connect to user");
        openServerSocket();
        sendRequestToTheServer(Commands.ALL, "");
        final String usersList = serverReader.readLine();
        final String[] users = usersList.split(",");
        for (var usersInfo : users) {
            final var parts = usersInfo.split(":");
            final var username = parts[ 0 ];
            final var userPort = Integer.parseInt(parts[ 1 ]);
            if (username.equals(receiver)) {
                user.connectToUser(userPort ,username);
                return true;
            }
        }
        System.out.println("not found" + receiver);
        serverSocket.close();
        return false;
    }

    private void openServerSocket() throws IOException {
        serverSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        serverReader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        serverWriter = new PrintWriter(serverSocket.getOutputStream(), true);
    }

    private void sendRequestToTheServer(final Commands command, final String content) {
        final var request = new ServerRequest(command, content);
        serverWriter.println(request.toJson());
    }
}
