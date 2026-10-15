package com.xztl.calculatorsocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CalculatorServer {

    public CalculatorServer(int port, int maxClient) {

        System.out.println("Server started on port: " + port);

        ExecutorService exec = Executors.newFixedThreadPool(maxClient);

        while (true) {

            // Apro una ServerSocket dentro le parentesi per farla chiudere
            // in automatico alla fine del blocco try-catch
            try (ServerSocket serverSocket = new ServerSocket(port)) {

                // Aspetto la connessione del client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected client: " + clientSocket.getInetAddress());

                // Creo l'oggetto che mi va a gestire il client
                ClientHandler client = new ClientHandler(clientSocket);

                // Mando l'oggetto del client all'Executor Service
                exec.submit(client);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }

    }

}