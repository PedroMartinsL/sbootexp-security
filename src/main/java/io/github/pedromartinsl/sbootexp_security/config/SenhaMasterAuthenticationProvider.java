package io.github.pedromartinsl.sbootexp_security.config;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import io.github.pedromartinsl.sbootexp_security.domain.security.CustomAuthentication;
import io.github.pedromartinsl.sbootexp_security.domain.security.IdentificacaoUsuario;

@Component
public class SenhaMasterAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //Se retornar um null ou uma exception, não terá uma authentication
        //recebe uma tentativa de authentication e retorna uma aprovação
        var login = authentication.getName();
        var senha = (String) authentication.getCredentials();

        String loginMaster = "master";
        String senhaMaster = "@321";

        if (loginMaster.equals(login) && senhaMaster.equals(senha)) {
            IdentificacaoUsuario identificacaoUsuario = new IdentificacaoUsuario("Sou Master", "Master", loginMaster, List.of("ADMIN"));
            return new CustomAuthentication(identificacaoUsuario);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
