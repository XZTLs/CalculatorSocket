package com.xztl.calculatorsocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CalculatorClient {
    
    public CalculatorClient(String ip, int port) {
        
        // Apro una Socket dentro le parentesi per farla chiudere
        // in automatico alla fine del blocco try-catch
        try (
            Socket socket = new Socket(ip, port);
            
            // Necessario per leggere l'input
            Scanner scanner = new Scanner(System.in);
        ){
            
            // Necessario per leggere stringhe
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Necessario per scrivere stringhe
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Leggo il primo numero dalla console
            String num1 = scanner.nextLine().toLowerCase().trim();

            // Leggo l'operatore dalla console
            String operator = scanner.nextLine().toLowerCase().trim();

            // Leggo il secondo numero dalla console
            String num2 = scanner.nextLine().toLowerCase().trim();
    
            // Mando l'espressione al Server
            out.println(String.format("%s %s %s", num1, operator, num2));

            // Aspetto la risposta del Server
            String response = in.readLine();
                
            System.out.println("Server response: " + response);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
