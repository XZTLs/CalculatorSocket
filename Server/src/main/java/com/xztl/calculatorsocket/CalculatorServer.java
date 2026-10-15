package com.xztl.calculatorsocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer {

    public CalculatorServer(int port) {

        System.out.println("Server started on port: " + port);

        while (true) {

            // Apro una ServerSocket dentro le parentesi per farla chiudere
            // in automatico alla fine del blocco try-catch
            try (ServerSocket serverSocket = new ServerSocket(port)) {

                // Aspetto la connessione del client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected client: " + clientSocket.getInetAddress());

                ClientHandler client = new ClientHandler(clientSocket);
                new Thread(client).start();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}