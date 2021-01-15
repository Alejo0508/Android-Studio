package com.example.endulsar;

public class Minicake {
    private  String Nombre;
    private String Precio;
    private String capacidad;
    private String imagen;

    public Minicake() {
    }

    public Minicake(String nombre, String precio, String capacidad, String imagen) {
        Nombre = nombre;
        Precio = precio;
        this.capacidad = capacidad;
        this.imagen = imagen;
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

    public String getImagen(int position) {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
