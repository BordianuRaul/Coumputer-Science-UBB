package com.example.domain;

import com.google.gson.Gson;

public abstract class Request {
    private final String content;

    protected Request(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
