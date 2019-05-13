package com.example.demo;

public class Usuario {
    public final String id;
    public final String nombre;
    public final String ciudad;
    public final String pais;

    public Usuario(String id, String nombre, String ciudad, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", pais=" + pais + '}';
    }

    
}
