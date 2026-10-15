package com.xztl.calculatorsocket;

public class Main {
    public static void main(String[] args) {

        // Porta del Server
        final int PORT = 5000;

        // Numero massimo di clients
        final int MAX_CLIENTS = 3;

        System.out.println("Server Started");

        CalculatorServer s = new CalculatorServer(PORT, MAX_CLIENTS);
    }
}