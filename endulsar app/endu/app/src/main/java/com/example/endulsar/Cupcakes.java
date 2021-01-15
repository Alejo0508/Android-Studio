package com.example.endulsar;

public class Cupcakes {
    private String id;
    private String Nombre;
    private  String Preciox6;
    private  String Preciox9;
    private  String capacidad;
    private String imagen;

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

    public String getPreciox6() {
        return Preciox6;
    }

    public void setPreciox6(String preciox6) {
        Preciox6 = preciox6;
    }

    public String getPreciox9() {
        return Preciox9;
    }

    public void setPreciox9(String preciox9) {
        Preciox9 = preciox9;
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

    public Cupcakes() {
    }

    public Cupcakes(String id, String nombre, String preciox6, String preciox9, String capacidad, String imagen) {
        this.id = id;
        Nombre = nombre;
        Preciox6 = preciox6;
        Preciox9 = preciox9;
        this.capacidad = capacidad;
        this.imagen = imagen;
    }

}
