package com.xztl.calculatorsocket;

public class Main {
    public static void main(String[] args) {

        // Porta del Server
        final int PORT = 5000;

        // Ip del server
        final String IP = "localhost";

        System.out.println("Client Started");

        new CalculatorClient(IP, PORT);
    }
}