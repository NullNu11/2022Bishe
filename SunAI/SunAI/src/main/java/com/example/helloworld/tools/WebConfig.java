package com.example.helloworld.tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

public class WebConfig {
    public String getToken() {
        String token;
        token = JWT.create().withAudience("11111").sign(Algorithm.HMAC256("22222"));
        return token;
    }
}