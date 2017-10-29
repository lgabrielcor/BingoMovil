package com.bingo.bingomaster.bingomovil;

import android.app.Application;

/**
 * Created by luisgabrielcorredorcombita on 28/10/17.
 */

public class GlobalApp extends Application {

    private static GlobalApp mInstance= null;

    protected GlobalApp(){}

    public static synchronized GlobalApp getInstance(){
        if(null == mInstance){
            mInstance = new GlobalApp();
        }
        return mInstance;
    }

    private String idUsuario;
    private String servidor;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }
}
