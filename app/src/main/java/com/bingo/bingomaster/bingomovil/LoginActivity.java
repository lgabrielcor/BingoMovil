package com.bingo.bingomaster.bingomovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bingo.bingomaster.bingomovil.controladores.LoginControlador;
import com.bingo.bingomaster.bingomovil.modelos.Login;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
    }

    public void LoginClick(View view){

        LoginControlador loginControlador = new LoginControlador();
        //obtener los datos de la pantalla
        Login loginData = new Login();
        extraerDatos(loginData);

        //autenticarse
        String resultado = loginControlador.autenticarse(loginData);

        if(resultado.equals("OK"))
        {
            Intent intent = new Intent(this, BingoManagerActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast toast =
                    Toast.makeText(getApplicationContext(),
                            resultado, Toast.LENGTH_LONG);

            toast.setGravity(Gravity.CENTER|Gravity.TOP,0,0);

            toast.show();
        }

    }

    private void extraerDatos(Login loginData) {
        loginData.setServidor(String.valueOf(((EditText)findViewById(R.id.txtservidor)).getText()));
        loginData.setUsuario(String.valueOf(((EditText)findViewById(R.id.txtusuario)).getText()));
        loginData.setPassword(String.valueOf(((EditText)findViewById(R.id.txtpassword)).getText()));
    }
}
