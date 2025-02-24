package io.github.pedromartinsl.sbootexp_security.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.pedromartinsl.sbootexp_security.domain.entity.Grupo;
import io.github.pedromartinsl.sbootexp_security.domain.entity.Usuario;
import io.github.pedromartinsl.sbootexp_security.domain.entity.UsuarioGrupo;
import io.github.pedromartinsl.sbootexp_security.domain.repository.GrupoRepository;
import io.github.pedromartinsl.sbootexp_security.domain.repository.UsuarioGrupoRepository;
import io.github.pedromartinsl.sbootexp_security.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final GrupoRepository grupoRepository;
    private final UsuarioGrupoRepository usuarioGrupoRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario salvar(Usuario usuario, List<String> grupos) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        repository.save(usuario);

        List<UsuarioGrupo> listaUsuarioGrupo = grupos.stream().map(nomeGrupo -> {
            Optional<Grupo> possivelGrupo = grupoRepository.findByNome(nomeGrupo);

            if (possivelGrupo.isPresent()) {
                Grupo grupo = possivelGrupo.get();
                return new UsuarioGrupo(usuario, grupo);
            }

            return null;
        })
                .filter(grupo -> grupo != null)
                .collect(Collectors.toList());
        usuarioGrupoRepository.saveAll(listaUsuarioGrupo);

        return usuario;
    }
}
