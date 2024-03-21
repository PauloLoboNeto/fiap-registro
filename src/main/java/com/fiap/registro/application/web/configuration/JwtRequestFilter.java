package com.fiap.registro.application.web.configuration;

import com.fiap.registro.domain.model.Usuario;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class JwtRequestFilter  extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtAccess;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
        Claims claims = null;

        if (authorizationHeader != null) {
            jwt = authorizationHeader.substring(7);
            username = jwtAccess.extractUsername(jwt);
            claims = jwtAccess.extractAllClaims(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Usuario usuario = Usuario.builder()
                    .id(UUID.fromString(claims.get("id", String.class)))
                    .nome(claims.get("nome", String.class))
                    .matricula(claims.get("matricula", String.class))
                    .email(claims.get("email", String.class))
                    .build();
            if (jwtAccess.validateToken(jwt, usuario)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        usuario, null, null);
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }

        filterChain.doFilter(request, response);

    }
}
