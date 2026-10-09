package com.xztl.calculatorsocket;

public class Main {
    public static void main(String[] args) {

        // Porta del Server
        final int PORT = 5000;

        System.out.println("Server Started");

        ServerEcho s = new ServerEcho(PORT);
    }
}