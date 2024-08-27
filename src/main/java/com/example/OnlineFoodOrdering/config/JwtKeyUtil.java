package com.example.OnlineFoodOrdering.config;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtKeyUtil {
    private static final SecretKey key = Keys.hmacShaKeyFor("nhsciejjakjsdbvgnhsmzpdghjmczxcmhjsvdmsfhjfnskjdfnklhuklndskbfudfb".getBytes());

    public static SecretKey getKey() {
        return key;
    }
}
