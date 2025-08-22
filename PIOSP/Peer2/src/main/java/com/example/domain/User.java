package com.example.domain;


import com.example.manager.Manager;
import com.example.utils.MessageUtils;
import com.example.server.ServerOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.utils.MessageUtils.BYE;

public class User {
    private final String username;
    private final ServerSocket serverSocket;
    private final Manager manager;
    private final MessageUtils messageHandler;
    private final List<Socket> sockets;
    private final ServerOperations serverOperations;
    private CustomReader reader;
    private PrintWriter mainWriter;
    private final AtomicInteger connections = new AtomicInteger(0);
    private final ExecutorService exec = Executors.newFixedThreadPool(1);

    private final List<String> usersConnected = new ArrayList<>();

    private final List<PrintWriter> writers = new ArrayList<>();

    private int indexMainWriter = 0;

    public User(String username, int port) throws IOException {
        this.username = username;
        this.serverSocket = new ServerSocket(port);
        this.serverOperations = new ServerOperations(this);
        this.reader = new CustomReader(new BufferedReader(new InputStreamReader(System.in)));
        this.mainWriter = new PrintWriter(System.out);
        this.manager = new Manager(this);
        this.messageHandler = new MessageUtils(this);
        this.sockets = new ArrayList<>();
    }

    public Socket getSockets() {
        return sockets.getFirst();
    }

    public void addSocket(Socket socket) {
        this.sockets.add(socket);
    }

    public CustomReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = new CustomReader(reader);
    }

    public PrintWriter getMainWriter() {
        return mainWriter;
    }

    public void setMainWriter(int index) {
        indexMainWriter = index;
        this.mainWriter = writers.get(index);
    }

    public int getNumberOfConnections() {
        return connections.get();
    }

    public synchronized ServerSocket getServerSocket() {
        return serverSocket;
    }

    public String getUsername() {
        return username;
    }

    public void initializeStreams() throws IOException {
        final Socket socket = sockets.get(increaseConnections());
        if (getNumberOfConnections() == 1) {
            this.setReader(new BufferedReader(new InputStreamReader(socket.getInputStream())));
        } else {
            this.reader.addReader(new BufferedReader(new InputStreamReader(socket.getInputStream())));
        }
        writers.add(new PrintWriter(socket.getOutputStream(), true));
        this.setMainWriter(writers.size() - 1);
    }

    public void acceptConnection() {
        try {
            if (serverSocket.isClosed()) {
                return;
            }
            this.addSocket(serverSocket.accept());
            initializeStreams();
            if (getNumberOfConnections() == 1) {
                exec.execute(manager::listenForMessages);
            }
        } catch (SocketException e) {
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        manager.sendMessage("!ack");
    }

    public void sendMessage(UserRequest userMessage) throws IOException {
        manager.sendMessage(userMessage.getContent());
    }

    public void connectToUser(int userPort, String username) throws IOException {
        manager.connectToUser(userPort);
        usersConnected.add(username);
    }

    public boolean connectToUserFromServer(String receiver) throws IOException {
        return serverOperations.connectToUserFromServer(receiver);
    }

    public void startMessageSession() {
        manager.listenForMessages();
    }

    public void closeConnectionWithUser() throws IOException {
        manager.closeConnection();
    }

    public void disconnectFromServer() throws IOException {
        serverOperations.disconnectFromServer();
    }

    public void sendByeToAllAndCloseServerSocket() throws IOException {
        for (int index = 0; index < sockets.size(); index++) {
            setMainWriter(index);
            sendMessage(new UserRequest(getUsername(), BYE));
            sockets.get(index).close();
        }
        serverSocket.close();
    }

    public int decreaseConnections() {
        return connections.decrementAndGet();
    }

    public void connectToServer() throws IOException {
        serverOperations.connectToServer();
    }

    public void handleConsoleInput() throws InterruptedException {
        messageHandler.handleConsoleInput(reader);
    }

    public int increaseConnections() {
        return connections.getAndIncrement();
    }

    public void addUsersConnected(String user) {
        usersConnected.add(user);
    }

    public void talkWithUser(String receiver) {
        System.out.println(receiver);
        for (int i = 0; i < usersConnected.size(); i++) {
            System.out.println(usersConnected.get(i));
            if (receiver.equals(usersConnected.get(i))) {
                setMainWriter(i);
                indexMainWriter = i;
            }
        }
    }

    public void closeMainSocket() throws IOException {
        Socket socket = sockets.remove(indexMainWriter);
        socket.close();
        if (decreaseConnections() == 0) {
            reader = new CustomReader(new BufferedReader(new InputStreamReader(System.in)));
        } else {
            reader.remove(indexMainWriter);
        }
        writers.remove(indexMainWriter);
        this.mainWriter = new PrintWriter(System.out);
    }

}
