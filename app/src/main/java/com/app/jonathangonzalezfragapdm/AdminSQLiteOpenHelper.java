package com.app.jonathangonzalezfragapdm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creacion de Tabla Usuarios (Ficticios)
        db.execSQL("create table usuarios (correo text primary key, nombre text, edad int, apellido text)");

        //Introdución de Usuarios Ficticios en la Tabla usuarios_ficticios
        //Mujeres
        db.execSQL("INSERT INTO usuarios (correo ,nombre, edad, apellido) VALUES ('Sara@usuarioficticon.com', 'Sara', 21, 'Jiménez') ");
        db.execSQL("INSERT INTO usuarios (correo ,nombre, edad, apellido) VALUES ('Anastasia@usuarioficticon.com', 'Anastasia', 22, 'De la Hoz') ");
        db.execSQL("INSERT INTO usuarios (correo ,nombre, edad, apellido) VALUES ('Dana@usuarioficticon.com', 'Dana', 20, 'Casas') ");
        db.execSQL("INSERT INTO usuarios (correo ,nombre, edad, apellido) VALUES ('Elena@usuarioficticon.com', 'Elena', 21, 'Balando') ");
        db.execSQL("INSERT INTO usuarios (correo ,nombre, edad, apellido) VALUES ('Laura@usuarioficticon.com', 'Laura', 25, 'Rodríguez') ");

        //Hombres
        db.execSQL("INSERT INTO usuarios (correo ,nombre, edad, apellido) VALUES ('Juan@usuarioficticon.com', 'Juan', 25, 'Rodríguez') ");
        db.execSQL("INSERT INTO usuarios (correo ,nombre, edad, apellido) VALUES ('Kike@usuarioficticon.com', 'Francisco', 22, 'Rodado') ");
        db.execSQL("INSERT INTO usuarios (correo ,nombre, edad, apellido) VALUES ('Eusebio@usuarioficticon.com', 'Eusebio', 28, 'Hernández') ");
        db.execSQL("INSERT INTO usuarios (correo ,nombre, edad, apellido) VALUES ('Luis@usuarioficticon.com', 'Luis', 20, 'González') ");
        db.execSQL("INSERT INTO usuarios (correo ,nombre, edad, apellido) VALUES ('Alvaro@usuarioficticon.com', 'Álvaro', 25, 'Pérez') ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
