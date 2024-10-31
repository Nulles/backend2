package org.example.backend;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Component
@Scope("singleton")
public class BackendServer {
    public BackendServer(){}

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String clientMessage;
            while (true) {
                if ((clientMessage = in.readLine()) == null){
                    clientMessage = "";
                }
                if (!clientMessage.isEmpty()){
                    Backend backend = new Backend();
                    if (clientMessage.equals("&&&ttt&&&")) {
                        out.println(backend.read());
                    }
                    else {
                        backend.write(clientMessage);
                        out.println("Saved successfully!");
                    }
                    break;
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}