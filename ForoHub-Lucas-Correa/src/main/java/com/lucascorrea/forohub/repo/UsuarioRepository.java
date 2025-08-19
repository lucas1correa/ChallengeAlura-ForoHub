package com.lucascorrea.forohub.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lucascorrea.forohub.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
