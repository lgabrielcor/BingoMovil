package com.bingo.bingomaster.bingomovil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bingo.bingomaster.bingomovil.controladores.LoginControlador;
import com.bingo.bingomaster.bingomovil.modelos.Login;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
        try {
            //autenticarse
            String resultado = loginControlador.autenticarse(loginData);

            //String resultado = "OK";
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

                showMessage("Autenticacion", "Datos de autenticacion incorrectos");
            }

         }catch(Exception ex){
            showMessage("Error", ex.getMessage());

        }


    }

    public void showMessage(String titulo, String mensaje){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void extraerDatos(Login loginData) {

        String pass = String.valueOf(((EditText)findViewById(R.id.txtpassword)).getText());

        com.bingo.bingomaster.bingomovil.servicios.cripto.criptoUtil cr = new com.bingo.bingomaster.bingomovil.servicios.cripto.criptoUtil();
        try {
            String enpass = cr.encrypt(pass);
            Log.d("INFO", "extraerDatos: "+enpass);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        loginData.setServidor(String.valueOf(((EditText)findViewById(R.id.txtservidor)).getText()));
        loginData.setUsuario(String.valueOf(((EditText)findViewById(R.id.txtusuario)).getText()));
        loginData.setPassword(String.valueOf(((EditText)findViewById(R.id.txtpassword)).getText()));


        //para servidor
        GlobalApp.getInstance().setPuerto(String.valueOf(((EditText)findViewById(R.id.txtpuerto)).getText()));
        GlobalApp.getInstance().setEsquema(String.valueOf(((EditText)findViewById(R.id.txtesquema)).getText()));
        GlobalApp.getInstance().setDBUsuario(String.valueOf(((EditText)findViewById(R.id.txtserveruser)).getText()));
        GlobalApp.getInstance().setDBPassword(String.valueOf(((EditText)findViewById(R.id.txtserverpassword)).getText()));

    }
}
