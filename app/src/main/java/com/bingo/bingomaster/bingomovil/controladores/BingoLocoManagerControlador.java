package com.bingo.bingomaster.bingomovil.controladores;

import com.bingo.bingomaster.bingomovil.GlobalApp;
import com.bingo.bingomaster.bingomovil.servicios.SQLServerServicios;

/**
 * Created by lgcorredor on 01/11/2017.
 */

public class BingoLocoManagerControlador {
    public String registrarJuego(String numero, String valor) {
        String respuesta="";
        try {
            respuesta = (new SQLServerServicios(GlobalApp.getInstance().getServidor())).registrarJuegoBingoLoco(numero, valor);
        } catch (Exception e) {
            //e.printStackTrace();
            return e.getMessage();
        }
        return respuesta;
    }
}
