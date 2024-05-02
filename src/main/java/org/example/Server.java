package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static final String FILES_PATH = "./Files/server" ;
    private ServerSocket serverSocket;
    public  static  final int PORT = 3030;

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);

            acceptConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void acceptConnection() {

        while (true) {
            try {
               Socket clientSocket = serverSocket.accept();

               if(clientSocket.isConnected()){
                   new Thread(() -> {
                       ClientConnection client = new ClientConnection(clientSocket);
                       try {
                           client.sendFile();
                       } catch (IOException e) {
                           throw new RuntimeException(e);
                       }

                   }).start();
               }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        new Server();
    }

}
