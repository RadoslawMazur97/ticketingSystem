package com.company;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Coin implements ICoin{
    private List<Double> listOfAllowedCoins = new ArrayList<>(Arrays.asList(0.10,0.20,0.50,1.0,2.0,5.0));
    private BigDecimal Value;
    private String Currency = "PLN";

    public Coin(double value){
        BigDecimal tmp = new BigDecimal(value);
        boolean flaga = FALSE;
        for (double C:listOfAllowedCoins
             ) {
            if(value == C){
                flaga = TRUE;
                this.Value = tmp;
                //System.out.println(Value);
            }
        }
        if (flaga == FALSE){
            System.out.println("Taka moneta nie istnieje");
        }

    }


    @Override
    public BigDecimal getValue() {
        return Value;
    }

    @Override
    public String getCurrency() {
        return Currency;
    }
}
