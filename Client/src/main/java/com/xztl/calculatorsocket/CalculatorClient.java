package com.xztl.calculatorsocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
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
            System.out.print("First number: ");
            String num1 = takeNumber(scanner);

            // Leggo l'operatore dalla console
            System.out.print("Operand: ");
            String operator = takeOperand(scanner);

            // Leggo il secondo numero dalla console
            System.out.print("Second number: ");
            String num2 = takeNumber(scanner);

            // Mando l'espressione al Server
            out.println(String.format("%s %s %s", num1, operator, num2));

            // Aspetto la risposta del Server
            String response = in.readLine();
                
            System.out.println("Server response: " + response);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String takeNumber(Scanner s) {
        String num = "";
        do {

            num = s.nextLine().toLowerCase().trim();

            // Controllo se la stringa inserita è un numero
            try { Double.parseDouble(num); } catch(NumberFormatException e) {
                System.out.println(num + " isn't a number");
                num = ""; 
            }

        } while (num.equals(""));
        return num;
    }

    private String takeOperand(Scanner s) {
        String[] accepted = new String[] {"+", "-", "*", "/"};

        String op = "";
        do {

            op = s.nextLine().toLowerCase().trim();

            // Controllo se la stringa inserita è negli operatori consentiti
            if (!Arrays.asList(accepted).contains(op)) {
                System.out.println(op + " is not an operand");
                op = "";
            }

        } while (op.equals(""));
        return op;
    }

}
