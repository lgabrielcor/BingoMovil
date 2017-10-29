package com.bingo.bingomaster.bingomovil.servicios;

import android.os.StrictMode;
import android.util.Log;

import com.bingo.bingomaster.bingomovil.modelos.Login;
import com.bingo.bingomaster.bingomovil.modelos.Usuario;
import net.sourceforge.jtds.jdbc.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by luisgabrielcorredorcombita on 28/10/17.
 */

public class SQLServerServicios {

    Connection conn;
    public SQLServerServicios(String servidor){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://"+servidor+";port=1433;databaseName=bingo;user=admin;password=admin;";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERROR", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
        conn=connection;
    }

    public Usuario autenticarse(Login login, String resultado) {
        resultado = "OK";
        return new Usuario();
    }

    public String salir(){
        return "OK";
    }
}
