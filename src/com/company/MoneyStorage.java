package com.company;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class MoneyStorage implements IMoneyStorage{
    private List<Coin> CoinsList = new ArrayList<>();
    private String currency;

    public MoneyStorage(String curr) {
        this.currency=curr;
    }

    @Override
    public void addCoin(Coin C) {
        CoinsList.add(C);

    }

    @Override
    public BigDecimal getSum() {
        BigDecimal tmp = new BigDecimal(0);
        for (Coin i:CoinsList
             ) {

            tmp = tmp.add(i.getValue());
        }
        return tmp;
    }

    @Override
    public Coin returnNominal(double value) {

        for (Coin C:CoinsList
             ) {
            if (C.getValue().floatValue() == value){
                CoinsList.remove(C);
                        return C;
            }

        }
        return null;
    }

    @Override
    public List<Coin> returnList() {
        return CoinsList;
    }

    @Override
    public void randomlistofcoins(List<Double> list){
        Random rand = new Random();
        int numberOfElements = 100;
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(list.size());
            double randomElement = list.get(randomIndex);
            this.addCoin(new Coin(randomElement));
        }
    }

    @Override
    public void deleteCoins(){
        CoinsList.clear();


    }
}
