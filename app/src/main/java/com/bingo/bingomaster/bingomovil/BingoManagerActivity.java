package com.bingo.bingomaster.bingomovil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bingo.bingomaster.bingomovil.controladores.BingoManagerControlador;
import com.bingo.bingomaster.bingomovil.controladores.LoginControlador;
import com.bingo.bingomaster.bingomovil.modelos.GameCard;
import com.bingo.bingomaster.bingomovil.servicios.contantes.Modo;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.sql.SQLException;

public class BingoManagerActivity extends AppCompatActivity {

    private String opcion;
    private String modulo;
    private int multiplicador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_bingo_manager);
        setOpcion(Modo.REGISTRAR);

        EditText modulo = findViewById(R.id.txtModulo);
        modulo.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    ((EditText)findViewById(R.id.txtModulo)).setText(enviaInformacionAServidor());
                    return true;
                }
                return false;
            }
        });
    }

    private void extraerDatos(){
         modulo = String.valueOf(((EditText)findViewById(R.id.txtModulo)).getText());
         multiplicador = Integer.getInteger(String.valueOf(((EditText)findViewById(R.id.txtProgresivo)).getText()));

    }

    public void btnScannerClick(View view){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("Escaner");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(true);
        integrator.setCaptureActivity(CaptureActivityPortrait.class);
        integrator.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Acción cancelada", Toast.LENGTH_LONG).show();
            } else {
                Log.d("MainActivity", "Scanned");
                ((EditText)findViewById(R.id.txtModulo)).setText(result.getContents());
                Toast.makeText(this, "Módulo: " + result.getContents(), Toast.LENGTH_LONG).show();
                ((EditText)findViewById(R.id.txtModulo)).setText(enviaInformacionAServidor());
            }
        }
    }

    private String enviaInformacionAServidor(){
        try{
            String resultado;
            GameCard gc = new GameCard();
            resultado= (new BingoManagerControlador()).enviaInformacionAServidor(modulo, multiplicador, opcion, gc);
            return resultado;
        }catch (Exception e){
            showMessage("Error", e.getMessage());
            return e.getMessage();
        }
    }

    //getters and setters
    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public void btnSalirClick(View view){
        try {
            (new LoginControlador()).Salir();
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage("Error", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Error", e.getMessage());
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

    public void btnRegistrarClick(View view) {
        setOpcion(Modo.REGISTRAR);
        TextView modo = (TextView) findViewById(R.id.lblModo);
        modo.setText(Modo.REGISTRAR.substring(0,1).toUpperCase()+Modo.REGISTRAR.substring(1));
        //input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public void btnRemoverClick(View view) {
        setOpcion(Modo.BORRAR);
        TextView modo = (TextView) findViewById(R.id.lblModo);
        modo.setText(Modo.BORRAR.substring(0,1).toUpperCase()+Modo.BORRAR.substring(1));
    }

    public void btnLibreClick(View view) {
        setOpcion(Modo.LIBRE);
        TextView modo = (TextView) findViewById(R.id.lblModo);
        modo.setText(Modo.LIBRE.substring(0,1).toUpperCase()+Modo.LIBRE.substring(1));
    }

    public void btnConsultarClick(View view) {
        setOpcion(Modo.CONSULTAR);
        TextView modo = (TextView) findViewById(R.id.lblModo);
        modo.setText(Modo.CONSULTAR.substring(0,1).toUpperCase()+Modo.CONSULTAR.substring(1));
    }
}
