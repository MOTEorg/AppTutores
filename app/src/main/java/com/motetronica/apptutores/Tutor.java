package com.motetronica.apptutores;

/**
 * Created by FELIPE on 21/11/2016.
 */

public class Tutor {
    private String cedula;
    private String nombre;
    private String profesion;
    private String nivel_academico;
    private String foto;
    private double calificacion;

    public Tutor(String name, String profesion, String cedula, String nivel, String foto) {
        this.cedula=cedula;
       this.nombre = name;
        this.profesion = profesion;
        this.calificacion=0.0;
        this.nivel_academico = nivel;
        this.foto = foto;
    }

    public Tutor(String name, String cedula, String nivel, String foto) {
        this.cedula=cedula;
        this.nombre = name;
        this.profesion = "";
        this.calificacion=0.0;
        this.nivel_academico = nivel;
        this.foto = foto;
    }

    public Tutor(String name, String cedula, double calificacion, String nivel, String foto) {
        this.cedula=cedula;
        this.nombre = name;
        this.profesion = "";
        this.calificacion=calificacion;
        this.nivel_academico = nivel;
        this.foto = foto;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel_academico() {
        return nivel_academico;
    }

    public void setNivel_academico(String nivel_academico) {
        this.nivel_academico = nivel_academico;
    }
}
