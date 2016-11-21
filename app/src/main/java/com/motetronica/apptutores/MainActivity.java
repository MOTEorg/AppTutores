package com.motetronica.apptutores;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    EditText usuario;
    EditText contrasena;
    String respuesta;
    public android.os.Handler h_consultas=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg){
            setRespuesta((String) msg.obj);
            Log.d("Response",respuesta);
            if (respuesta!=null){
            if (!respuesta.equals("0")){
            Intent i=new Intent(MainActivity.this, Busqueda.class);
            i.putExtra("Cedula",respuesta);
            startActivity(i);
        }
        }
        }
    };

    GeneradorConsultas consultas = new GeneradorConsultas(this.h_consultas);
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
            //Lo que se hace luego se encuentra en el Handler en HandleMessage
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("envio fallido","error_envio");
        }

    }
    void ir_registrar(View v){
        Log.d("Respuesta",respuesta);
    }
    public void setRespuesta(String resp){
        respuesta=resp;
    }

    public String getRespuesta(){
        return respuesta;
    }
}
