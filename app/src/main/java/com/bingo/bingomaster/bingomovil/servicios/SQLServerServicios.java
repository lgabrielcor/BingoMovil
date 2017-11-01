package com.bingo.bingomaster.bingomovil.servicios;

import android.os.StrictMode;
import android.util.Log;

import com.bingo.bingomaster.bingomovil.GlobalApp;
import com.bingo.bingomaster.bingomovil.modelos.GameCard;
import com.bingo.bingomaster.bingomovil.modelos.GameCard_Insert_Params;
import com.bingo.bingomaster.bingomovil.modelos.Login;
import com.bingo.bingomaster.bingomovil.modelos.Usuario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by luisgabrielcorredorcombita on 28/10/17.
 */

public class SQLServerServicios {

    Connection conn;
    public SQLServerServicios(String servidor) throws SQLException, ClassNotFoundException, Exception {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://"+servidor
                    +";port="+GlobalApp.getInstance().getPuerto()
                    +";databaseName="+GlobalApp.getInstance().getEsquema()
                    +";user="+ GlobalApp.getInstance().getDBUsuario()
                    +";password="+GlobalApp.getInstance().getDBPassword()+";";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
            throw se;
        } catch (ClassNotFoundException e) {
            Log.e("ERROR", e.getMessage());
            throw e;
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
            throw e;
        }
        conn=connection;
    }

    public Usuario autenticarse(Login login, StringBuilder resultado) throws SQLException {

        String query = "SELECT u.Id, u.Password, u.Role, u.EmployeeId, E.Active, LoggedIn, e.Name NameEmployee"+
                " FROM AppUser u INNER JOIN Employee E ON E.[Id] = u.EmployeeId WHERE u.Id = '" + login.getUsuario() + "'";
        ResultSet rs = null;

        Usuario usuario = new Usuario();

        try {
            rs = conn.createStatement().executeQuery(query);
            //obtener solo el primer registro
            if(!rs.wasNull()){
                while (rs.next()){
                    usuario.setId(rs.getString(1));
                    usuario.setPassword(rs.getString(2));
                    usuario.setRole(rs.getInt(3));
                    usuario.setEmployeeId(rs.getInt(4));
                    usuario.setActive(rs.getBoolean(5));
                    usuario.setLoggedIn(rs.getBoolean(6));
                    usuario.setNameEmployee(rs.getString(7));
                    GlobalApp.getInstance().setEmployeeId(usuario.getEmployeeId());
                    GlobalApp.getInstance().setIdUsuario(usuario.getId());
                    resultado.append("OK");
                    break;
                }
                logueado("true");
            }
            else{
                resultado.append("Fail");
            }
            conn.close();
        } catch (SQLException e) {
            Log.e("ERROR", e.getMessage());
            throw e;
        }

        return usuario;
    }

    public String logueado(String estado){
        String queryUpdate="UPDATE AppUser SET LoggedIn = '"+estado+"' WHERE Id = '" + GlobalApp.getInstance().getIdUsuario() + "'";

        int resultado = 0;
        try {
            resultado = conn.createStatement().executeUpdate(queryUpdate);
            GlobalApp.getInstance().setIdUsuario(null);
            GlobalApp.getInstance().setEmployeeId(0);
            GlobalApp.getInstance().setServidor(null);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(resultado>0)
            return "OK";
        else
            return "Fail";
    }

    public GameCard GameCardInsert(GameCard_Insert_Params paramsDeLectura) throws SQLException {

        /*
          @ModuleCode AS INT,
          @ModuleRead AS VARCHAR(20),
          @Multiplier AS INT,
          @Source AS INT,
          @EmployeeId AS INT
        * */
        GameCard gc =  new GameCard();

        CallableStatement cstmt = conn.prepareCall("{? = call dbo.GameCard_Insert(?,?,?,?,?)}");
        cstmt.registerOutParameter(1, java.sql.Types.INTEGER);

        cstmt.setString(3, paramsDeLectura.getModuleRead());
        cstmt.setInt(2, Integer.parseInt(extraeNumero(paramsDeLectura.getModuleRead())));
        cstmt.setInt(4, paramsDeLectura.getMultiplier());
        cstmt.setInt(5, paramsDeLectura.getSource());
        cstmt.setInt(6, GlobalApp.getInstance().getEmployeeId());
        ResultSet rs = cstmt.executeQuery();

        while(rs.next()){
            gc.setGameId(rs.getInt(1));
            gc.setEmployeeId(rs.getInt(2));
            gc.setGameNumber(rs.getInt(3));
            gc.setQtyModules(rs.getInt(4));
            gc.setQtyProgressive(rs.getInt(5));
            gc.setTotal(rs.getInt(6));
            break;
        }

        cstmt.getMoreResults();
        paramsDeLectura.setReturn_value(cstmt.getInt(1));

        cstmt.close();
        conn.close();
        return gc;
    }

    public GameCard GameCardDelete(GameCard_Insert_Params paramsDeLectura) throws SQLException {
        GameCard gc =  new GameCard();
        CallableStatement cstmt = conn.prepareCall("{? = call dbo.GameCard_Delete(?,?)}");
        cstmt.registerOutParameter(1, java.sql.Types.INTEGER);

        cstmt.setInt(2, Integer.parseInt(extraeNumero(paramsDeLectura.getModuleRead())));
        cstmt.setInt(3, GlobalApp.getInstance().getEmployeeId());
        ResultSet rs = cstmt.executeQuery();
        cstmt.getMoreResults();
        paramsDeLectura.setReturn_value(cstmt.getInt(1));
        while(rs.next()){
            gc.setGameId(rs.getInt(1));
            gc.setEmployeeId(rs.getInt(2));
            gc.setGameNumber(rs.getInt(3));
            gc.setQtyModules(rs.getInt(4));
            gc.setQtyProgressive(rs.getInt(5));
            gc.setTotal(rs.getInt(6));
            break;
        }

        cstmt.close();
        conn.close();
        return gc;
    }

    public void GameCardQuery(GameCard_Insert_Params paramsDeLectura) throws SQLException {
        //dbo.GameCard_Consult
        CallableStatement cstmt = conn.prepareCall("{? = call dbo.GameCard_Consult(?)}");
        cstmt.registerOutParameter(1, java.sql.Types.INTEGER);

        cstmt.setInt(2, Integer.parseInt(extraeNumero(paramsDeLectura.getModuleRead())));
        cstmt.execute();
        cstmt.getMoreResults();
        paramsDeLectura.setReturn_value(cstmt.getInt(1));

        cstmt.close();
        conn.close();
    }

    private String extraeNumero(String codigo){
        StringBuilder str = new StringBuilder();
        char[] strarray = codigo.toCharArray();
        for (char c : strarray)
        {
            if(Character.isDigit(c)){
                str.append(c);
            }
        }

        if(str.length()<1){
            return "0";
        }
        else{
            return str.toString();
        }
    }

    public String registrarJuego(String numero, String valor) throws SQLException {

        CallableStatement cstmt = conn.prepareCall("{? = call dbo.GameCard_Insert(?,?,?,?,?)}");
        cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
        cstmt.setString(2, numero);
        cstmt.setString(3, valor);

        ResultSet rs = cstmt.executeQuery();
        cstmt.getMoreResults();
        int resultado = (cstmt.getInt(1));

        cstmt.close();
        conn.close();

        return "OK";
    }
}
