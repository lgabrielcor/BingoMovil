package com.bingo.bingomaster.bingomovil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bingo.bingomaster.bingomovil.controladores.BingoLocoManagerControlador;

public class bingo_loco_manager extends AppCompatActivity {

    private String numero;
    private String valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingo_loco_manager);
    }

    private void btnJugarClick(View view){
        extraerDatos();
        BingoLocoManagerControlador bingoLoco = new BingoLocoManagerControlador();
        String respuesta;
        respuesta = bingoLoco.registrarJuego(numero, valor);
    }
    private void extraerDatos(){
       setNumero(((EditText)findViewById(R.id.txtNumero)).getText().toString());
       setValor(((EditText)findViewById(R.id.txtValor)).getText().toString());
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
