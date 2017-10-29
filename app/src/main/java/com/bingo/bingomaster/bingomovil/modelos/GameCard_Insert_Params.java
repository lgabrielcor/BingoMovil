package com.bingo.bingomaster.bingomovil.modelos;

/**
 * Created by luisgabrielcorredorcombita on 28/10/17.
 */

public class GameCard_Insert_Params {
    private String ModuleRead;
    private int Multiplier;
    private int Source;
    private int EmployeeId;
    private int return_value;

    public String getModuleRead() {
        return ModuleRead;
    }

    public void setModuleRead(String moduleRead) {
        ModuleRead = moduleRead;
    }

    public int getMultiplier() {
        return Multiplier;
    }

    public void setMultiplier(int multiplier) {
        Multiplier = multiplier;
    }

    public int getSource() {
        return Source;
    }

    public void setSource(int source) {
        Source = source;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public int getReturn_value() {
        return return_value;
    }

    public void setReturn_value(int return_value) {
        this.return_value = return_value;
    }
}
