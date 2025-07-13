package com.src.week1;
import java.security.SecureRandom;
import java.util.Base64;

public class JwtSecretGenerator {
    public static void main(String[] args) {
        byte[] key = new byte[64]; // 512 bits
        new SecureRandom().nextBytes(key);
        String secret = Base64.getEncoder().encodeToString(key);
        System.out.println("Your JWT secret key: " + secret);
    }
}
