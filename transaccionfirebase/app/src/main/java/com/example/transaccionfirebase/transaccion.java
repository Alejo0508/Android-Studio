package com.example.transaccionfirebase;

public class transaccion {

    private String fecha;
    private String hora;
    private String nrocuentadestino;
    private String nrocuentaorigen;
    private String valor;

    public transaccion(){

    }

    public transaccion(String fecha, String hora, String nrocuentadestino, String nrocuentaorigen, String valor) {
        this.fecha = fecha;
        this.hora = hora;
        this.nrocuentadestino = nrocuentadestino;
        this.nrocuentaorigen = nrocuentaorigen;
        this.valor = valor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNrocuentadestino() {
        return nrocuentadestino;
    }

    public void setNrocuentadestino(String nrocuentadestino) {
        this.nrocuentadestino = nrocuentadestino;
    }

    public String getNrocuentaorigen() {
        return nrocuentaorigen;
    }

    public void setNrocuentaorigen(String nrocuentaorigen) {
        this.nrocuentaorigen = nrocuentaorigen;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
