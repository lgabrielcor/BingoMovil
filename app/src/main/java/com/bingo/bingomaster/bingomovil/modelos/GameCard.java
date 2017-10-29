package com.bingo.bingomaster.bingomovil.modelos;

/**
 * Created by luisgabrielcorredorcombita on 28/10/17.
 */

public class GameCard {
    private int GameId;
    private int EmployeeId;
    private int GameNumber;
    private int qtyModules;
    private int qtyProgressive;
    private int total;

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int gameId) {
        GameId = gameId;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public int getGameNumber() {
        return GameNumber;
    }

    public void setGameNumber(int gameNumber) {
        GameNumber = gameNumber;
    }

    public int getQtyModules() {
        return qtyModules;
    }

    public void setQtyModules(int qtyModules) {
        this.qtyModules = qtyModules;
    }

    public int getQtyProgressive() {
        return qtyProgressive;
    }

    public void setQtyProgressive(int qtyProgressive) {
        this.qtyProgressive = qtyProgressive;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
