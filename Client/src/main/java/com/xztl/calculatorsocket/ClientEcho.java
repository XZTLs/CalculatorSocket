package com.xztl.calculatorsocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientEcho {
    
    public ClientEcho(String ip, int port) {

        // Necessario per leggere l'input
        Scanner scanner = new Scanner(System.in);

        String message = "";

        // Apro una Socket dentro le parentesi per farla chiudere
        // in automatico alla fine del blocco try-catch
        try (Socket socket = new Socket(ip, port);) {
            
            // Necessario per leggere stringhe
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Necessario per scrivere stringhe
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            while (!message.equals("exit")) {

                // Leggo una linea dalla console, che poi diventerà il messaggio
                // da inviare al Server
                message = scanner.nextLine().toLowerCase().trim();
    
                // Mando il messaggio al Server
                out.println(message);
                
                // Aspetto la risposta del Server
                String response = in.readLine();
                
                System.out.println("Server response: " + response);

            }


        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
