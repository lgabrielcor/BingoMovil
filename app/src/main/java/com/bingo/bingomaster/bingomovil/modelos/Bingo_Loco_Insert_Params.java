package com.bingo.bingomaster.bingomovil.modelos;

/**
 * Created by luisgabrielcorredorcombita on 28/10/17.
 */

public class Bingo_Loco_Insert_Params {
    private int Number;
    private int MoneyValue;
    private int ReturnValue;
    private String Message;

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public int getMoneyValue() {
        return MoneyValue;
    }

    public void setMoneyValue(int moneyValue) {
        MoneyValue = moneyValue;
    }

    public int getReturnValue() {
        return ReturnValue;
    }

    public void setReturnValue(int returnValue) {
        ReturnValue = returnValue;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
