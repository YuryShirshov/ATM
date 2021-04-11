package ru.sber.atm.data.balance;

import lombok.*;
import ru.sber.atm.data.Currency;

import java.math.BigDecimal;

/**
 * Суперкласс представления информации о балансе
 */
@Data
public abstract class Balance {
    @NonNull protected BigDecimal sum;
    @Setter(AccessLevel.NONE)
    @NonNull protected Currency currency;

    abstract public void setSum(BigDecimal sum) throws Exception;
}
