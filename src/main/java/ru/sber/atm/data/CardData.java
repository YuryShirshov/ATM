package ru.sber.atm.data;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Класс представления информации о карте
 */
@Data
public class CardData {
    private String number;
    private String holder;
    private int cvc2;
    private LocalDate expiryDate;
    private BigDecimal balance;
}
