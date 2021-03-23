package com.jp.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.ib.custom.toast.CustomToastView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAceptar;
    private EditText txtName;
    private EditText txtApellido;
    private EditText txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAceptar=findViewById(R.id.btnAceptar);
        txtName=findViewById(R.id.txtName);
        txtApellido=findViewById(R.id.txtApellido);
        txtEmail=findViewById(R.id.txtEmail);
        btnAceptar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAceptar) {
            String name = txtName.getText().toString();
            String apellido = txtApellido.getText().toString();
            String email = txtEmail.getText().toString();

            if (name.isEmpty()) {
                CustomToastView.makeInfoToast(this,"Error al validar nombre",R.layout.custom_toast).show();
                return;
            }
            if (apellido.isEmpty()) {
                CustomToastView.makeInfoToast(this,"Error al validar Apellido",R.layout.custom_toast).show();
                return;
            }
            if (!isValidEmail(email)) {
                CustomToastView.makeInfoToast(this,"Error al validar Email",R.layout.custom_toast).show();
                return;
            }
            Intent myIntent= new Intent(this,Calculadoraimc.class);
            myIntent.putExtra("nameCalc",name);
            myIntent.putExtra("apellidoCalc",apellido);
            myIntent.putExtra("emailCalc",email);
            startActivity(myIntent);
        }
    }
        private Boolean isValidEmail(String email) {
            Pattern pattern = Patterns.EMAIL_ADDRESS;
            return pattern.matcher(email).matches();

        }
}