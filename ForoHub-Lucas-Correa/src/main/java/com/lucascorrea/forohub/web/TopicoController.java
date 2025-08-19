package com.lucascorrea.forohub.web;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.lucascorrea.forohub.domain.Topico;
import com.lucascorrea.forohub.domain.Usuario;
import com.lucascorrea.forohub.repo.TopicoRepository;
import com.lucascorrea.forohub.repo.UsuarioRepository;
import com.lucascorrea.forohub.web.dto.DatosListadoTopico;
import com.lucascorrea.forohub.web.dto.DatosRegistroTopico;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    public TopicoController(TopicoRepository tr, UsuarioRepository ur){this.topicoRepository=tr; this.usuarioRepository=ur;}

    @GetMapping
    public ResponseEntity<List<DatosListadoTopico>> listar(){
        var list = topicoRepository.findAll().stream()
            .map(t -> new DatosListadoTopico(t.getId(), t.getTitulo(), t.getMensaje(), t.getCurso(), t.getFechaCreacion(),
                    t.getAutor()!=null ? t.getAutor().getEmail() : null))
            .toList();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody @Valid DatosRegistroTopico dto, Authentication auth){
        Usuario autor = usuarioRepository.findByEmail(auth.getName()).orElse(null);
        Topico t = new Topico();
        t.setTitulo(dto.getTitulo()); t.setMensaje(dto.getMensaje()); t.setCurso(dto.getCurso());
        t.setAutor(autor);
        Topico saved = topicoRepository.save(t);
        return ResponseEntity.status(201).body(saved.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        if(!topicoRepository.existsById(id)) return ResponseEntity.notFound().build();
        topicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
