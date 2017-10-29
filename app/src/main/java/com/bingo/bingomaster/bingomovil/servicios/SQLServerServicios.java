package com.bingo.bingomaster.bingomovil.servicios;

import android.os.StrictMode;
import android.util.Log;

import com.bingo.bingomaster.bingomovil.GlobalApp;
import com.bingo.bingomaster.bingomovil.modelos.GameCard;
import com.bingo.bingomaster.bingomovil.modelos.GameCard_Insert_Params;
import com.bingo.bingomaster.bingomovil.modelos.Login;
import com.bingo.bingomaster.bingomovil.modelos.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        String query = "SELECT u.Id, u.Password, u.Role, u.EmployeeId, E.Active, LoggedIn, e.Name NameEmployee"+
                " FROM AppUser u INNER JOIN Employee E ON E.[Id] = u.EmployeeId WHERE u.Id = '" + login + "'";
        ResultSet rs = null;

        Usuario usuario = new Usuario();

        try {
            rs = conn.createStatement().executeQuery(query);
            //obtener solo el primer registro
            if(!rs.wasNull()){
                while (rs.next()){
                    usuario.setId(rs.getString(0));
                    usuario.setPassword(rs.getString(1));
                    usuario.setRole(rs.getInt(2));
                    usuario.setEmployeeId(rs.getInt(3));
                    usuario.setActive(rs.getBoolean(4));
                    usuario.setLoggedIn(rs.getBoolean(5));
                    usuario.setNameEmployee(rs.getString(6));
                    GlobalApp.getInstance().setEmployeeId(usuario.getEmployeeId());
                    GlobalApp.getInstance().setIdUsuario(usuario.getId());
                    resultado = "OK";
                    break;
                }

            }
            else{
                resultado = "Fail";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



        return usuario;
    }

    public String salir(){
        String queryUpdate="UPDATE AppUser SET LoggedIn = 'false' WHERE Id = '" + GlobalApp.getInstance().getIdUsuario() + "'";

        int resultado = 0;
        try {
            resultado = conn.createStatement().executeUpdate(queryUpdate);
            GlobalApp.getInstance().setIdUsuario(null);
            GlobalApp.getInstance().setEmployeeId(0);
            GlobalApp.getInstance().setServidor(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(resultado>0)
            return "OK";
        else
            return "Fail";
    }

    public GameCard GameCardInsert(GameCard_Insert_Params paramsDeLectura) throws SQLException {
        GameCard gc =  new GameCard();

        String SPsql = "EXEC dbo.GameCard_Insert ?,?";   // for stored proc taking 2 parameters
        Connection con = conn;   // java.sql.Connection
        PreparedStatement ps = con.prepareStatement(SPsql);
        ps.setEscapeProcessing(true);
        ps.setQueryTimeout(15);
        ps.setString(1, paramsDeLectura.getModuleRead());
        ps.setInt(2, paramsDeLectura.getMultiplier());
        ResultSet rs = ps.executeQuery();


        return gc;
    }

    public GameCard GameCardDelete(GameCard_Insert_Params paramsDeLectura) throws SQLException {
        GameCard gc =  new GameCard();

        String SPsql = "EXEC dbo.GameCard_Delete ?,?";   // for stored proc taking 2 parameters
        Connection con = conn;   // java.sql.Connection
        PreparedStatement ps = con.prepareStatement(SPsql);
        ps.setEscapeProcessing(true);
        ps.setQueryTimeout(15);
        ps.setString(1, paramsDeLectura.getModuleRead());
        ps.setInt(2, paramsDeLectura.getMultiplier());
        ResultSet rs = ps.executeQuery();
        return gc;
    }

    public void GameCardQuery(GameCard_Insert_Params paramsDeLectura) throws SQLException {
        //dbo.GameCard_Consult
        String SPsql = "EXEC dbo.GameCard_Consult ?,?";   // for stored proc taking 2 parameters
        Connection con = conn;   // java.sql.Connection
        PreparedStatement ps = con.prepareStatement(SPsql);
        ps.setEscapeProcessing(true);
        ps.setQueryTimeout(15);
        ps.setString(1, paramsDeLectura.getModuleRead());
        ps.setInt(2, paramsDeLectura.getMultiplier());
        ResultSet rs = ps.executeQuery();
    }
}
