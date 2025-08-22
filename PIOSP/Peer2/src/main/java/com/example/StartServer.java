package com.example;

import com.example.server.Server;

public class StartServer {
    public static void main(String[] args) {
        final var server = new Server();
        server.run();
    }
}
