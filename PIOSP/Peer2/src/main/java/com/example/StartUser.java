package com.example;

import com.example.domain.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartUser {

    private static final ExecutorService exec = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws IOException {
        final var inputStreamReader = new InputStreamReader(System.in);
        final var reader = new BufferedReader(inputStreamReader);

        System.out.println("Enter your username: ");
        final var username = reader.readLine();

        System.out.println("Enter a port: ");
        final var port = Integer.parseInt(reader.readLine());

        final var user = new User(username, port);

        final var managerThread = new Manager(user);
        final var messageReaderThread = new MessageReaderThread(user);
        final ConnectionHandlerThread connectionHandlerThread = new ConnectionHandlerThread(user);

        exec.execute(managerThread);
        exec.execute(messageReaderThread);
        exec.execute(connectionHandlerThread);
    }

    public static class Manager extends Thread {
        private final User user;

        public Manager(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            try {
                user.connectToServer();
            } catch (IOException e) {
                System.out.println("Could not connect to server : " + e.getMessage());
            }
        }
    }

    public static class MessageReaderThread extends Thread {
        private final User user;

        public MessageReaderThread(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            try {
                user.handleConsoleInput();
            } catch (final InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static class ConnectionHandlerThread extends Thread {
        private final User user;

        public ConnectionHandlerThread(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            while (true) {
                user.acceptConnection();
            }
        }
    }
}
