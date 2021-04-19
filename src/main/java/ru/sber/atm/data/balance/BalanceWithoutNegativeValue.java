package ru.sber.atm.data.balance;

import lombok.NonNull;
import ru.sber.atm.enums.Currency;

import java.math.BigDecimal;

/**
 * Класс представления информации о балансе без возможности отрицательного баланса
 */
public class BalanceWithoutNegativeValue extends Balance{

    public BalanceWithoutNegativeValue(@NonNull BigDecimal sum, @NonNull Currency currency) {
        super(sum, currency);
    }

    @Override
    public void setSum(BigDecimal sum) throws Exception {
        if (sum.doubleValue() < 0) {
            throw new Exception("Баланс не может быть отрицательным");
        } else {
            this.sum = sum;
        }
    }
}
