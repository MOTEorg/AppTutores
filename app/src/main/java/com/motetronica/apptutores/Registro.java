package com.motetronica.apptutores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class Registro extends AppCompatActivity {

    EditText txtcedula;
    EditText txtnombre;
    EditText txtcontrasena;
    EditText txtmail;
    EditText txtapellido;

    String mail;
    String cedula;
    String nombre;
    String apellido;
    String contrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mail=getIntent().getStringExtra("Mail");
        contrasena=getIntent().getStringExtra("Password");
        txtmail=(EditText) findViewById(R.id.txtMail);
        txtcontrasena=(EditText) findViewById(R.id.txtConfContra);
        txtmail.setText(mail);
        txtcontrasena.setText(contrasena);
    }
}
