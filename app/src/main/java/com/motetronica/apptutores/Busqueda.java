package com.motetronica.apptutores;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Busqueda extends AppCompatActivity implements Tutores.OnFragmentInteractionListener {
    String cedula_usuario;
    Button btbuscar;
    EditText txtnombre;
    EditText txtmateria;



    String respuesta;
    Tutores tutoresFragment;

    public android.os.Handler h_consultas=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg){
            if (msg.what==2){
                Toast.makeText(getApplicationContext(), (String) msg.obj,
                        Toast.LENGTH_LONG).show();
            }else {
                setRespuesta((String) msg.obj);
                Log.d("Response",respuesta);
                if (respuesta!=null){
                    if (respuesta.length()>3){
                        tutoresFragment = (Tutores)
                                getSupportFragmentManager().findFragmentById(R.id.lista_tutores_container);
                        if (tutoresFragment == null) {
                            tutoresFragment = tutoresFragment.newInstance(respuesta,cedula_usuario);
                            getSupportFragmentManager().beginTransaction()
                                    .add(R.id.lista_tutores_container, tutoresFragment)
                                    .commit();
                        }else{
                            tutoresFragment.mtutoresAdapter.clear();
                            tutoresFragment = (Tutores)
                                    getSupportFragmentManager().findFragmentById(R.id.lista_tutores_container);
                            tutoresFragment = tutoresFragment.newInstance(respuesta,cedula_usuario);
                            getSupportFragmentManager().beginTransaction()
                                    .add(R.id.lista_tutores_container, tutoresFragment)
                                    .commit();
                        }
                    }
                    else{
                        Toast.makeText(Busqueda.this, "No existen resultados", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(Busqueda.this, "No resultados, fallo en la conexion", Toast.LENGTH_LONG).show();
                }
            }
        }
    };
    public void setRespuesta(String resp){
            respuesta=resp;
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        cedula_usuario=getIntent().getStringExtra("Cedula_usuario");
        Log.d("Cedula", cedula_usuario);
        btbuscar=(Button) findViewById(R.id.buttonbuscar);


        /*spinnerdia = (Spinner) findViewById(R.id.seleccionarDia);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dias_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerdia.setAdapter(adapter);



        spinnerhora = (Spinner) findViewById(R.id.seleccionarHora);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterh = ArrayAdapter.createFromResource(this,
                R.array.horas_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerhora.setAdapter(adapterh);


        spinnerdia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Busqueda.this, "Seleccionado> " +parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });*/

        txtmateria=(EditText) findViewById(R.id.buscarMateria);
        txtnombre=(EditText)findViewById(R.id.buscarNombre);
    }
    void ResultadoBusqueda(View v){

        try {
            GeneradorConsultas consultas = new GeneradorConsultas(h_consultas);

            ArrayList<String> claves = new ArrayList<String>();
            ArrayList<String> valores = new ArrayList<String>();
            claves.add("nombre");
            claves.add("materia");
            valores.add(txtnombre.getText().toString());
            valores.add(txtmateria.getText().toString());
            String consult = consultas.formarPares(claves, valores);
            consultas.sendConsultaPost("buscar_tutores.php",consult);
        }
        catch (NullPointerException e){
            Log.d("error", "No hay datos");
        }

        btbuscar.setText("Buscar de nuevo");
    }
    @Override
    public void onFragmentInteraction(Uri uri){}




}
