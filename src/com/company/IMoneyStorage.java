package com.company;

import java.math.BigDecimal;
import java.util.List;

public interface IMoneyStorage {

    void addCoin(Coin C);
    BigDecimal getSum();
    Coin returnNominal(double value);
    List<Coin> returnList();
    void randomlistofcoins(List<Double> list);
    void deleteCoins();

}
