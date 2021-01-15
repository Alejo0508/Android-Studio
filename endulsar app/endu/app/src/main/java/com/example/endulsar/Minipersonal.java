package com.example.endulsar;

public class Minipersonal {
    private String id;
    private String Nombre;
    private  String Precio;
    private  String capacidad;
    private String imagen;

    public Minipersonal() {
    }

    public Minipersonal(String id, String nombre, String precio, String capacidad, String imagen) {
        this.id = id;
        Nombre = nombre;
        Precio = precio;
        this.capacidad = capacidad;
        this.imagen = imagen;
    }

    public String getImagen(int position) {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }
}


