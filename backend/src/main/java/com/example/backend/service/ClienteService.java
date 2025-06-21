package com.example.backend.service;

public class ClienteService {

    public static String gerarSenhaTemporaria() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = new java.util.Random().nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
}
