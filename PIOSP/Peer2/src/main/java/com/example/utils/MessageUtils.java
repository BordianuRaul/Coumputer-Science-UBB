package com.example.utils;

import com.example.domain.CustomReader;
import com.example.domain.User;
import com.example.domain.UserRequest;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageUtils {
    public static final String BYE = "!bye";
    public static final String BYEBYE = "!byebye";
    public static final String ACK = "!ack";
    private final String TALK = "!talk";
    public final static String HELLO = "!hello";
    private final User user;
    private final ExecutorService exec = Executors.newFixedThreadPool(1);

    public MessageUtils(User user) {
        this.user = user;
    }


    public void handleConsoleInput(CustomReader reader) throws InterruptedException {
        while (true) {
            System.out.println("<" + user.getUsername() + ">");
            try {
                processMessage(reader.readLine());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void processMessage(String message) throws IOException, InterruptedException {
        {
            //System.out.println("a intrat cu mesajul " + message);
            if (message.startsWith(TALK)) {
                final var receiver = message.split(" ")[ 1 ];
                processTalkMessage(receiver);
            } else if (message.startsWith(HELLO)) {
                final var receiver = message.split(" ")[ 1 ];
                processHelloMessage(receiver);
            } else if (message.startsWith(BYEBYE)) {
                processByeByeMessage();
            } else if (message.equals(BYE)) {
                processByeMessage();
            } else {
                try {
                    user.sendMessage(new UserRequest(user.getUsername(), message));
                } catch (IOException e) {
                    System.out.println("Could not process the input message" + e.getMessage());
                }
            }
        }
    }

    private void processTalkMessage(String receiver) {
        user.talkWithUser(receiver);
    }

    private void processByeByeMessage() throws IOException {
        user.sendByeToAllAndCloseServerSocket();
        user.disconnectFromServer();
        exec.shutdown();
        System.out.println("User disconnected");
        System.exit(0);
    }

    private void processByeMessage() throws IOException {
        user.sendMessage(new UserRequest(user.getUsername(), BYE));
        user.closeConnectionWithUser();
        System.out.println("Closed connection");
    }

    private void processHelloMessage(String receiver) throws InterruptedException, IOException {
        try {
            System.out.println("processhelloMessage");
            final var connectionStatus = user.connectToUserFromServer(receiver);
            System.out.println("Connection status: " + connectionStatus);
            if (connectionStatus) {
                System.out.println("a trimis mesajul catre " + user.getUsername() + " " + user.getMainWriter());
                user.getMainWriter().println(new UserRequest(user.getUsername(), HELLO).toJson());
                final var ackMessage =
                        UserRequest.fromJson(user.getReader().readLine()).getContent();
                if (ackMessage.equals(ACK)) {
                    System.out.println("Connection with " + receiver);
                    if (user.getNumberOfConnections() == 1) {
                        exec.execute(user::startMessageSession);
                    }
                } else {
                    user.closeConnectionWithUser();
                    user.getMainWriter().println(new UserRequest(user.getUsername(), BYE).toJson());
                    System.out.println("Connection failed. Did not receive ack message");
                }
            }
        } catch (IOException e) {
            exec.shutdown();
            throw new IOException("Error processing !hello message: " + e.getMessage());
        }
    }
}

