package com.example.domain;

import com.google.gson.Gson;

public class UserRequest extends Request {
    private final String sender;

    public UserRequest(String sender, String content) {
        super(content);
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public static UserRequest fromJson(String json) {
        return new Gson().fromJson(json, UserRequest.class);
    }
}
