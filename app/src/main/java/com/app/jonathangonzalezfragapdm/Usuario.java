package com.app.jonathangonzalezfragapdm;

public class Usuario {
    private String uid;
    private String nombre;
    private String correo;
    private String fecha_nacimiento;
    private String genero;
    private String descripcion;
    private String muestrame;
    private String busco;
    private int latitud;
    private int longitid;
    private int diamonds;
    private int arrows;



    public Usuario(String uid, String nombre, String correo, String fecha_nacimiento, String genero, String descripcion, String muestrame, String busco, int latitud, int longitid) {
        this.uid = uid;
        this.nombre = nombre;
        this.correo = correo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.genero = genero;
        this.descripcion = descripcion;
        this.muestrame = muestrame;
        this.busco = busco;
        this.latitud = latitud;
        this.longitid = longitid;
        this.diamonds = 0;
        this.arrows = 1;
    }

    public Usuario() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMuestrame() {
        return muestrame;
    }

    public void setMuestrame(String muestrame) {
        this.muestrame = muestrame;
    }

    public String getBusco() {
        return busco;
    }

    public void setBusco(String busco) {
        this.busco = busco;
    }

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public int getLongitid() {
        return longitid;
    }

    public void setLongitid(int longitid) {
        this.longitid = longitid;
    }

    public int getDiamonds() {
        return diamonds;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }

    public int getArrows() {
        return arrows;
    }

    public void setArrows(int arrows) {
        this.arrows = arrows;
    }
}
