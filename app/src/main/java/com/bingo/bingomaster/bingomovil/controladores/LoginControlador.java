package com.bingo.bingomaster.bingomovil.controladores;

import com.bingo.bingomaster.bingomovil.GlobalApp;
import com.bingo.bingomaster.bingomovil.modelos.Login;
import com.bingo.bingomaster.bingomovil.modelos.Usuario;
import com.bingo.bingomaster.bingomovil.servicios.SQLServerServicios;

/**
 * Created by luisgabrielcorredorcombita on 28/10/17.
 */

public class LoginControlador {
    public String autenticarse(Login login){
        String resultado = "";
        Usuario usuario = (new SQLServerServicios(login.getServidor())).autenticarse(login, resultado);
        GlobalApp.getInstance().setIdUsuario(usuario.getId());
        GlobalApp.getInstance().setServidor(login.getServidor());

        return resultado;
    }

    public String Salir(){
        return (new SQLServerServicios(GlobalApp.getInstance().getServidor())).salir();
    }
}