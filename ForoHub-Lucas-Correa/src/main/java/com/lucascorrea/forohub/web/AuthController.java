package com.lucascorrea.forohub.web;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.lucascorrea.forohub.domain.Usuario;
import com.lucascorrea.forohub.repo.UsuarioRepository;
import com.lucascorrea.forohub.security.TokenService;
import com.lucascorrea.forohub.web.dto.LoginDTO;
import com.lucascorrea.forohub.web.dto.TokenDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;
    public AuthController(AuthenticationManager am, TokenService ts, UsuarioRepository ur, PasswordEncoder encoder){
        this.authManager=am; this.tokenService=ts; this.usuarioRepository=ur; this.encoder=encoder;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO dto){
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        String token = tokenService.generarToken(dto.getEmail());
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid LoginDTO dto){
        if(usuarioRepository.findByEmail(dto.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("El email ya está registrado");
        }
        Usuario u = new Usuario();
        u.setNombre(dto.getEmail());
        u.setEmail(dto.getEmail());
        u.setPassword(encoder.encode(dto.getPassword()));
        u.setRol("ROLE_USER");
        usuarioRepository.save(u);
        return ResponseEntity.ok().build();
    }
}
