package com.motetronica.apptutores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Busqueda extends AppCompatActivity {
    String cedula_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        cedula_usuario=getIntent().getStringExtra("Cedula");
    }
}
