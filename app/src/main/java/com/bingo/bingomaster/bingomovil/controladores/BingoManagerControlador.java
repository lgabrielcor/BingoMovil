package com.bingo.bingomaster.bingomovil.controladores;

import com.bingo.bingomaster.bingomovil.GlobalApp;
import com.bingo.bingomaster.bingomovil.servicios.SQLServerServicios;
import com.bingo.bingomaster.bingomovil.servicios.contantes.Modo;
import com.bingo.bingomaster.bingomovil.modelos.*;

import java.sql.SQLException;

/**
 * Created by luisgabrielcorredorcombita on 28/10/17.
 */

public class BingoManagerControlador {
    public String enviaInformacionAServidor(String modulo, int multiplicador, String opcion, GameCard gc) {
        String resultado="";
        if(opcion.equals(Modo.REGISTRAR)){
            GameCard_Insert_Params paramsDeLectura = new GameCard_Insert_Params();
            paramsDeLectura.setEmployeeId(GlobalApp.getInstance().getEmployeeId());
            paramsDeLectura.setModuleRead(modulo);
            paramsDeLectura.setMultiplier(multiplicador);
            paramsDeLectura.setSource(0);
            try {
                gc = (new SQLServerServicios(GlobalApp.getInstance().getServidor())).GameCardInsert(paramsDeLectura);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            resultado = procesaResultadoRegistrar(paramsDeLectura.getReturn_value());

        }
        else if(opcion.equals(Modo.BORRAR)){

            GameCard_Insert_Params paramsDeLectura = new GameCard_Insert_Params();
            paramsDeLectura.setEmployeeId(GlobalApp.getInstance().getEmployeeId());
            paramsDeLectura.setModuleRead(modulo);
            try {
                gc = (new SQLServerServicios(GlobalApp.getInstance().getServidor())).GameCardDelete(paramsDeLectura);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultado = procesaResultadoBorrar(paramsDeLectura.getReturn_value());
        }
        else if (opcion.equals(Modo.LIBRE)){
            GameCard_Insert_Params paramsDeLectura = new GameCard_Insert_Params();
            paramsDeLectura.setModuleRead(modulo);
            paramsDeLectura.setEmployeeId(GlobalApp.getInstance().getEmployeeId());
            paramsDeLectura.setMultiplier(multiplicador);
            paramsDeLectura.setSource(3);//para registrar carton de bingo free

            try {
                gc = (new SQLServerServicios(GlobalApp.getInstance().getServidor())).GameCardInsert(paramsDeLectura);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            resultado = procesaResultadoRegistrar(paramsDeLectura.getReturn_value());
        }
        else if (opcion.equals(Modo.CONSULTAR)){
            GameCard_Insert_Params paramsDeLectura = new GameCard_Insert_Params();
            paramsDeLectura.setModuleRead(modulo);

            try {
                (new SQLServerServicios(GlobalApp.getInstance().getServidor())).GameCardQuery(paramsDeLectura);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultado = procesaResultadoConsultar(paramsDeLectura.getReturn_value());
        }

        return resultado;
    }

    private String procesaResultadoConsultar(int return_value) {
        switch (return_value)
        {
            case -1:
                return "Error: No existe juego abierto";

            case -2:
                return "Error: No existe juego abierto";

            case -3:
                return "Error: No existe juego abierto";

            case -4:
                return "Error: No existe juego abierto";

            case -5:
                return "Error: No existe juego abierto";

            case 0:
                return "Error: No existe juego abierto";

            case -1001:
                return "Error: Error de base de datos";

            case -1000:
                return "Error: Error de aplicación";

            default:
                return "Error desconocido, comuniquese con su administrador: Error" + return_value;

        }
    }

    private String procesaResultadoBorrar(int return_value) {
        switch (return_value)
        {
            case -1:
                return "Error: No existe juego abierto";

            case -2:
                return "Error: Módulo no registrado en el Sistema";

            case -3:
                return "Error: Módulo no registrado en el juego actual";

            case -6:
                return "Error: Usuario no registró el módulo";

            case 0:
                return "Error: Borrado no se pudo realizar";

            case -1001:
                return "Error: Error de base de datos";

            case -1000:
                return "Error: Error de aplicación";

            default:
                return "Error desconocido, comuniquese con su administrador: Error" + return_value;

        }
    }

    private String procesaResultadoRegistrar(int return_value) {

        switch (return_value)
        {
            case -1:
                return "Error: No existe juego abierto";

            case -2:
                return "Error: No existe juego abierto";

            case -3:
                return "Error: No existe juego abierto";

            case -4:
                return "Error: No existe juego abierto";

            case -5:
                return "Error: No existe juego abierto";

            case 0:
                return "Error: No existe juego abierto";

            case -1001:
                return "Error: Error de base de datos";

            case -1000:
                return "Error: Error de aplicación";

            default:
                return "Error desconocido, comuniquese con su administrador: Error" + return_value;

        }
    }
}
