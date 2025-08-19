package com.lucascorrea.forohub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lucascorrea.forohub.domain.Usuario;
import com.lucascorrea.forohub.repo.UsuarioRepository;

@SpringBootApplication
public class ForohubApplication {
    public static void main(String[] args) {
        SpringApplication.run(ForohubApplication.class, args);
    }

    @Bean
    CommandLineRunner initUsers(UsuarioRepository repo, PasswordEncoder encoder) {
        return args -> {
            repo.findByEmail("admin@forohub.com").orElseGet(() -> {
                Usuario u = new Usuario();
                u.setNombre("Administrador");
                u.setEmail("admin@forohub.com");
                u.setPassword(encoder.encode("123456"));
                u.setRol("ROLE_ADMIN");
                return repo.save(u);
            });
        };
    }
}
