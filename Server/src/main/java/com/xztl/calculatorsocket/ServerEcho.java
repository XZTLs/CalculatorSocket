package com.xztl.calculatorsocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerEcho {

    public ServerEcho(int port) {
        String message = "";

        // Apro una ServerSocket dentro le parentesi per farla chiudere
        // in automatico alla fine del blocco try-catch
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port: " + port);
            
            // Aspetto la connessione del client
            Socket client = serverSocket.accept();
            
            // Necessario per leggere stringhe
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            // Necessario per scrivere stringhe
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            
            while (!message.equals("exit")) {

                // Aspetto un messaggio dal Client
                message = in.readLine();

                System.out.println("Message received: " + message);

                // Mando un messaggio al Client
                out.println(message.toUpperCase());

            }

        } catch (IOException e) {
            System.out.println(e);
        }

    }

}