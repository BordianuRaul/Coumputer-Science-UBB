package com.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class CustomReader {
    private final List<BufferedReader> readers;
    private final ReentrantLock lock = new ReentrantLock();

    public CustomReader(final BufferedReader initial) {
        readers = new ArrayList<>();
        readers.add(initial);
    }

    public void addReader(BufferedReader reader) {
        lock.lock();
        try {
            readers.add(reader);
        } finally {
            lock.unlock();
        }
    }

    public boolean ready() throws IOException, InterruptedException {
        Thread.sleep(2000);
        lock.lock();
        try {
            for (BufferedReader reader : readers) {
                if (reader.ready()) {
                    return true;
                }
            }
            return false;
        } finally {
            lock.unlock();
        }
    }


    public String readLine() throws IOException, InterruptedException {
        while (true) {
            if (ready()) break;
        }
        for (BufferedReader reader : readers) {
            if (reader.ready()) {
                String line = reader.readLine();
                if (line != null && !line.isBlank()) {
                    return line;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "CustomReader{" +
                "readers=" + readers.getFirst().toString() +
                '}';
    }

    public void remove(int index) {
        lock.lock();
        try {
            readers.remove(index);
        } finally {
            lock.unlock();
        }
    }

}
