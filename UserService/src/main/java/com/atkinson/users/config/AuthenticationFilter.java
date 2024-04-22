package com.atkinson.users.config;


import com.atkinson.users.model.Login;
import com.atkinson.users.repository.UserDTO;
import com.atkinson.users.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;
    private Environment env;

    public AuthenticationFilter(
            AuthenticationManager authenticationManager,
            UserService userService,
            Environment env) {
        super(authenticationManager);
        this.userService = userService;
        this.env = env;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response)
            throws AuthenticationException {
        try {
            Login credentials = new ObjectMapper()
                    .readValue(request.getInputStream(), Login.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUserName(),
                            credentials.getPassword(),
                            new ArrayList<>()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication auth)
            throws IOException, ServletException {
        String userName = ((User)auth.getPrincipal()).getUsername();
        UserDTO userDetails = userService.getUserByUserName(userName);
        Instant now = Instant.now();
        SecretKey key = Keys.hmacShaKeyFor(Base64.getEncoder()
                .encode(new String(Objects
                        .requireNonNull(env.getProperty("token.secret")).getBytes()).getBytes()));
        String token = Jwts.builder()
                .subject(userDetails.getUserId())
                .expiration(Date
                        .from(now
                                .plusMillis(Long
                                        .parseLong(Objects
                                                .requireNonNull(env.getProperty("token.expiration"))))))
                .issuedAt(Date.from(now))
                .signWith(key)
                .compact();

        response.addHeader("token", token);
        response.addHeader("userId", userDetails.getUserId());
    }
}
