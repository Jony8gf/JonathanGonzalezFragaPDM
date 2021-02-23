package com.app.jonathangonzalezfragapdm;

import java.util.ArrayList;

public class ListaUsuarios {

    String usuarioCorreo;
    ArrayList<Usuario> list = new ArrayList<>();

    public ListaUsuarios(String usuarioCorreo, ArrayList<Usuario> list) {
        this.usuarioCorreo = usuarioCorreo;
    }

    public ListaUsuarios() {
    }

    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }

    public ArrayList<Usuario> getList() {
        return list;
    }

    public void setList(ArrayList<Usuario> list) {
        this.list = list;
    }
}
