package com.example.miniface;

import java.util.Date;

public class Mensaje {
    String mensaje;
    boolean lado;
    Date fecha;

    public Mensaje(String mensaje, boolean lado,Date fecha) {
        this.mensaje = mensaje;
        this.lado = lado;
        this.fecha=fecha;
    }
}
