package com.motetronica.apptutores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GeneradorConsultas consultas = new GeneradorConsultas();
    EditText usuario;
    EditText contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

    }

    void pantalla_inicio(View v){
        setContentView(R.layout.activity_main);
    }
    void fin (View v){
        finish();
    }

    void ingresar(View v){
        //consulta
        usuario = (EditText) findViewById(R.id.txtuser);
        contrasena = (EditText) findViewById(R.id.txtpass);
        ArrayList<String> claves = new ArrayList<String>();
        ArrayList<String> valores = new ArrayList<String>();
        claves.add("nombre");
        claves.add("contrasena");
        valores.add(usuario.getText().toString());
        valores.add(contrasena.getText().toString());
        String consult = consultas.formarPares(claves, valores);
        try {
            consultas.sendConsultaPost("consultar_existencia.php",consult);
            String resp = consultas.respuesta;
            Log.d("Respuesta",resp);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("envio fallido","error_envio");
        }

    }
}
