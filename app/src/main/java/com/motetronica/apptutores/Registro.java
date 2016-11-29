package com.motetronica.apptutores;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Registro extends AppCompatActivity {

    EditText txtcedula;
    EditText txtnombre;
    EditText txtcontrasena;
    EditText txtconfirmacion;
    EditText txtmail;
    EditText txtapellido;
    RadioButton rbtutor;
    RadioButton rbestudiante;

    String mail;
    String cedula;
    String nombre;
    String apellido;
    String contrasena;
    String confcontra;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mail=getIntent().getStringExtra("Mail");
        contrasena=getIntent().getStringExtra("Password");
        txtmail=(EditText) findViewById(R.id.txtMail);
        txtcontrasena=(EditText) findViewById(R.id.txtConfContra);
        txtnombre=(EditText) findViewById(R.id.txtNombres);
        rbestudiante=(RadioButton) findViewById(R.id.rbestudiante);
        rbtutor=(RadioButton)findViewById(R.id.rbtutor);
        txtapellido=(EditText) findViewById(R.id.txtApellidos);
        txtcedula=(EditText) findViewById(R.id.txtCedula);
        txtconfirmacion=(EditText) findViewById(R.id.txtConfContra);
        txtmail.setText(mail);
        txtcontrasena.setText(contrasena);


    }
    void Registrar (View v){
        //Recoger Valores
        mail=txtmail.getText().toString();
        nombre=txtnombre.getText().toString();
        apellido=txtapellido.getText().toString();
        cedula=txtcedula.getText().toString();
        contrasena=txtcontrasena.getText().toString();
        confcontra=txtconfirmacion.getText().toString();
        if (cedula.length()==10){
        //Contrasenas iguales
        if (contrasena.equals(confcontra.toString())){
        ArrayList<String> claves=new ArrayList<>();
        ArrayList<String> valores=new ArrayList<>();

        claves.add("cedula");
        valores.add(cedula);
        claves.add("mail");
        valores.add(mail);
        claves.add("nombre");
        valores.add(nombre);
        claves.add("contrasena");
        valores.add(contrasena);
        claves.add("apellido");
        valores.add(apellido);
            Intent i=new Intent(Registro.this, RegistroCont.class);
            i.putStringArrayListExtra("Claves",claves);
            i.putStringArrayListExtra("Valores",valores);
        if (rbtutor.isChecked()){
            i.putExtra("isTutor",true);
            startActivity(i);
        }
        else if (rbestudiante.isChecked()){
            i.putExtra("isTutor",false);
            startActivity(i);
        }
        else{
            Toast.makeText(this, "Seleccione Tutor o Estudiante", Toast.LENGTH_SHORT).show();
        }
        }else{
            Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }
        }else{
            Toast.makeText(this, "La cédula debe tener 10 dígitos", Toast.LENGTH_SHORT).show();
        }

    }

    void Regresar(View v){
        super.onBackPressed();
    }
}
