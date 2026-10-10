package com.xztl.calculatorsocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer {

    public CalculatorServer(int port) {

        // Apro una ServerSocket dentro le parentesi per farla chiudere
        // in automatico alla fine del blocco try-catch
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port: " + port);

            while (true) {

                // Aspetto la connessione del client
                Socket client = serverSocket.accept();

                // Necessario per leggere stringhe
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                // Necessario per scrivere stringhe
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);

                // Aspetto un messaggio dal Client
                String exp = in.readLine();

                System.out.println("Expression received: " + exp);

                // Calcolo il risultato
                try {

                    Double result = calculateExpression(exp);

                    // Mando un messaggio al Client
                    out.println(result);

                } catch (ArithmeticException e) {
                    out.println(e.getMessage());
                }

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public double calculateExpression(String exp) throws ArithmeticException {
        // Divido la stringa per gli spazi
        String[] l = exp.split(" ");
        double num1 = Double.parseDouble(l[0]);
        String operator = l[1];
        double num2 = Double.parseDouble(l[2]);

        // Uso uno switch per capire l'operatore
        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new ArithmeticException(String.format("%s is not a valid operator", operator));
        };
    }

}