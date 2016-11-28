package com.motetronica.apptutores;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HorarioTutor extends AppCompatActivity {
    //Cedula del Tutor
    String cedula;
    String cedula_usuario;
    GestorHorario gestorHorario;

    String respuesta;


    public android.os.Handler h_consultas=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg){
                setRespuesta((String) msg.obj);
                Log.d("Response", respuesta);
                if (respuesta!=null ){
                    if (respuesta.length()>3){
                gestorHorario=new GestorHorario(respuesta);
                    ArrayList<Integer> txtviewstodos=gestorHorario.getPlantilla();
                    for (int i=0; i<txtviewstodos.size(); i++){
                        TextView txt=(TextView) findViewById(txtviewstodos.get(i));
                        txt.setText(getString(R.string.equis));
                        txt.setBackgroundColor(getResources().getColor(R.color.Rojo));
                    }
                    ArrayList<Integer> txtviews=gestorHorario.getHorarios();
                    for (int i=0; i<txtviews.size(); i++){
                    TextView txt=(TextView) findViewById(txtviews.get(i));
                        //Log.d ("Modificando", String.valueOf(i));
                        txt.setText(getString(R.string.vistobueno));
                        txt.setBackgroundColor(getResources().getColor(R.color.Verde));
                    }
                }else{
                    gestorHorario=new GestorHorario();
                    ArrayList<Integer> txtviewstodos=gestorHorario.getPlantilla();
                    for (int i=0; i<txtviewstodos.size(); i++){
                        TextView txt=(TextView) findViewById(txtviewstodos.get(i));
                        txt.setText(getString(R.string.equis));
                        txt.setBackgroundColor(getResources().getColor(R.color.Rojo));
                    }
                }
                }

        }
    };
    GeneradorConsultas consulta=new GeneradorConsultas(h_consultas);
    public void setRespuesta(String resp){
        respuesta=resp;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_tutor);
        cedula=getIntent().getStringExtra("Cedula_tutor");
        cedula_usuario=getIntent().getStringExtra("Cedula_usuario");

        consulta.sendConsultaPost("consultar_horario.php","cedula="+cedula);
    }
    void Regresar(View v){
        super.onBackPressed();
    }
    void PedirClase(View v){
        //Intent i=new Intent(HorarioTutor.this, Peticion.class);
        //startActivity(i);
        Toast.makeText(this, "Peticion de clase al tutor con cedula: "+cedula, Toast.LENGTH_LONG).show();
    }
}
