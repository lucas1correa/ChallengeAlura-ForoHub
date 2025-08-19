package com.lucascorrea.forohub.security;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.lucascorrea.forohub.domain.Usuario;
import com.lucascorrea.forohub.repo.UsuarioRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioRepository repo;
    public UserDetailsServiceImpl(UsuarioRepository repo){this.repo=repo;}
    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u=repo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado"));
        List<GrantedAuthority> auth=List.of(new SimpleGrantedAuthority(u.getRol()));
        return new User(u.getEmail(), u.getPassword(), auth);
    }
}