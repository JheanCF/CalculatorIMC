package com.jp.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ib.custom.toast.CustomToastView;

public class Calculadoraimc extends AppCompatActivity implements View.OnClickListener {

    private Button btnCalculator;
    private TextView tvInfo;
    private EditText txtHeight;
    private EditText txtWeight;
    private TextView tvResult;
    private ImageView imResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadoraimc);
        Intent intent=getIntent();
        String name= intent.getStringExtra("nameCalc");
        String apellido= intent.getStringExtra("apellidoCalc");
        String email= intent.getStringExtra("emailCalc");
        String msg ="Hola "+ name + " "+apellido + "es un gusto que este aca el informe es al correo "+ email;

        tvInfo=findViewById(R.id.tvInfo);
        tvResult=findViewById(R.id.tvResult);
        txtHeight=findViewById(R.id.txtHeight);
        txtWeight=findViewById(R.id.txtWeight);
        btnCalculator=findViewById(R.id.btncalculator);
        imResult=findViewById(R.id.imResult);
        tvInfo.setText(msg);
        btnCalculator.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btncalculator){
            String peso = txtWeight.getText().toString();
            String altura = txtHeight.getText().toString();
            if (peso.isEmpty()) {
                CustomToastView.makeInfoToast(this,"NO ha ingresado su peso",R.layout.custom_toast).show();
                return;
            }
            if (altura.isEmpty()) {
                CustomToastView.makeInfoToast(this,"NO ha ingresado su altura",R.layout.custom_toast).show();
                return;
            }
           Float imc=calcularIMC(Float.parseFloat(peso),Float.parseFloat(altura));
            String msgIMC ="Hola tu IMC es de --->"+imc;
            if (imc<18.5){
              tvResult.setText(msgIMC+" \nBAJO PESO");
              imResult.setImageResource(R.drawable.bajopeso);
            }
            if (imc>18.5 && imc<=24.9){
                tvResult.setText(msgIMC+"\nPESO NORMAL");
                imResult.setImageResource(R.drawable.normal);
            }
            if (imc>25.0 && imc<=29.9){
                tvResult.setText(msgIMC+"\nSOBREPESO");
                imResult.setImageResource(R.drawable.speso);
            }
            if (imc>30.0 && imc<=34.9){
                tvResult.setText(msgIMC+"\nOBESIDAD I");
                imResult.setImageResource(R.drawable.obesidad);
            }
            if (imc>35.0 && imc<=39.9){
                tvResult.setText(msgIMC+"\nOBESIDAD II");
                imResult.setImageResource(R.drawable.obesidadii);
            }
            if (imc>40){
                tvResult.setText(msgIMC+"OBESIDAD III");
                imResult.setImageResource(R.drawable.obesidadii);
            }
        }
    }
    private float calcularIMC(float peso, float altura){

        return (peso/(altura*altura));
    }
}