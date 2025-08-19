package com.lucascorrea.forohub.web.dto;
import jakarta.validation.constraints.NotBlank;
public class DatosRegistroTopico {
    @NotBlank private String titulo;
    @NotBlank private String mensaje;
    @NotBlank private String curso;
    public String getTitulo(){return titulo;} public void setTitulo(String t){this.titulo=t;}
    public String getMensaje(){return mensaje;} public void setMensaje(String m){this.mensaje=m;}
    public String getCurso(){return curso;} public void setCurso(String c){this.curso=c;}
}