package com.example.endulsar;

public class Tortica {
    private  String Nombre;
    private String Precio;
    private String capacidad;
    private String imagen;

    public Tortica(){

    }

    public Tortica(String nombre, String precio, String capacidad, String imagen, String id) {
        Nombre = nombre;
        Precio = precio;
        this.capacidad = capacidad;
        this.imagen = imagen;
        this.id = id;
    }

    public String getImagen(int position) {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    private String id;

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

