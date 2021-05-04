package ru.sber.atm.data.balance;

import lombok.NonNull;
import ru.sber.atm.enums.Currency;

import java.math.BigDecimal;

/**
 * Класс представления информации о балансе с возможностью отрицательного баланса
 */
public class BalanceWithNegativeValue extends Balance{

    public BalanceWithNegativeValue(@NonNull BigDecimal sum, @NonNull Currency currency) {
        super(sum, currency);
    }

    @Override
    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
