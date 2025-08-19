package com.lucascorrea.forohub.web.dto;
import java.time.LocalDateTime;
public class DatosListadoTopico {
    private Long id; private String titulo; private String mensaje; private String curso; private LocalDateTime fechaCreacion; private String autor;
    public DatosListadoTopico(Long id,String titulo,String mensaje,String curso,LocalDateTime fechaCreacion,String autor){
        this.id=id;this.titulo=titulo;this.mensaje=mensaje;this.curso=curso;this.fechaCreacion=fechaCreacion;this.autor=autor;}
    public Long getId(){return id;} public String getTitulo(){return titulo;} public String getMensaje(){return mensaje;}
    public String getCurso(){return curso;} public LocalDateTime getFechaCreacion(){return fechaCreacion;} public String getAutor(){return autor;}
}