package io.github.pedromartinsl.sbootexp_security.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.pedromartinsl.sbootexp_security.domain.security.CustomAuthentication;
import io.github.pedromartinsl.sbootexp_security.domain.security.IdentificacaoUsuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String secretHeader = request.getHeader("x-secret");

        if (secretHeader != null) {
            if (secretHeader.equals("secre3t")) {
                var identificacaoUsuario = new IdentificacaoUsuario("id-secret", "Muito secreto", "x-secret", List.of("USER"));
                Authentication authentication = new CustomAuthentication(identificacaoUsuario); // o Token é para criar a authentication

                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response); //prosseguindo o filtro da cadeia
    }

}
