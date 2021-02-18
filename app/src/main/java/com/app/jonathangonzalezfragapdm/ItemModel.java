package com.app.jonathangonzalezfragapdm;

public class ItemModel {
    private int image;
    private String nombre, edad, apellido;

    public ItemModel() {
    }

    public ItemModel(int image, String nombre, String edad, String apellido) {
        this.image = image;
        this.nombre = nombre;
        this.edad = edad;
        this.apellido = apellido;
    }

    public int getImage() {
        return image;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEdad() {
        return edad;
    }

    public String getApellido() {
        return apellido;
    }
}
