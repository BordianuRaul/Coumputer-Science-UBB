package com.example.server;

import com.example.domain.Commands;
import com.example.domain.Request;
import com.google.gson.Gson;

public class ServerRequest extends Request {
    private final Commands command;

    public ServerRequest(Commands command, String content) {
        super(content);
        this.command = command;
    }

    public Commands getCommand() {
        return command;
    }

    public static ServerRequest fromJson(String json) {
        return new Gson().fromJson(json, ServerRequest.class);
    }
}
