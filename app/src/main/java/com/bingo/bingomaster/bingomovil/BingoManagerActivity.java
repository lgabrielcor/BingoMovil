package com.bingo.bingomaster.bingomovil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BingoManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_bingo_manager);
    }

    private void extraerDatos(){

    }

    private String consulta(){
        return "resultado";
    }
}
