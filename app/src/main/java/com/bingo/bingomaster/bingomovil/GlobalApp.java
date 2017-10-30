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
    private int employeeId;
    private String Puerto;
    private String DBUsuario;
    private String DBPassword;
    private String Esquema;

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

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getPuerto() {
        return Puerto;
    }

    public void setPuerto(String puerto) {
        Puerto = puerto;
    }

    public String getDBUsuario() {
        return DBUsuario;
    }

    public void setDBUsuario(String DBUsuario) {
        this.DBUsuario = DBUsuario;
    }

    public String getDBPassword() {
        return DBPassword;
    }

    public void setDBPassword(String DBPassword) {
        this.DBPassword = DBPassword;
    }

    public String getEsquema() {
        return Esquema;
    }

    public void setEsquema(String esquema) {
        Esquema = esquema;
    }
}
