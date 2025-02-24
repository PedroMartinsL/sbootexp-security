package io.github.pedromartinsl.sbootexp_security.api.dto;

import java.util.List;

import io.github.pedromartinsl.sbootexp_security.domain.entity.Usuario;
import lombok.Data;

@Data
public class CadastroUsuarioDTO {
    private Usuario usuario;
    private List<String> permissoes;
}
