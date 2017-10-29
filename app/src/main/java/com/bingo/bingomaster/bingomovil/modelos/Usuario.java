package com.bingo.bingomaster.bingomovil.modelos;

/**
 * Created by luisgabrielcorredorcombita on 28/10/17.
 */

public class Usuario {
    public Usuario(){}
    private String Id;
    private String Nombre;
    private String Password;
    private String Servidor;
    private int Role;
    private int EmployeeId;
    private String NameEmployee;
    private boolean Active;
    private boolean LoggedIn;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getServidor() {
        return Servidor;
    }

    public void setServidor(String servidor) {
        Servidor = servidor;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public String getNameEmployee() {
        return NameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        NameEmployee = nameEmployee;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public boolean isLoggedIn() {
        return LoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        LoggedIn = loggedIn;
    }
}
