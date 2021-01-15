package com.example.wsphpmysql;

public class usuario {

    // se crea esta clase de java para crear un arreglo JSON que almecene datos


    // se crean atributos en esta clase que tengan el mismo nombre de la tabla de sql
    private String usr;
    private  String nombre;
    private  String clave;
    private String correo;


    // se genera un metodo constructor vacio y los metodos get y set dandole clic derecho en generate

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public usuario()
    {



    }

}
