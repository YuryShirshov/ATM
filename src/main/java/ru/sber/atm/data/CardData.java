package ru.sber.atm.data;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

/**
 * Класс представления информации о карте
 */
@Data
public class CardData {
    @NonNull private int pin;
    @NonNull private String number;
    @NonNull private int cvc2;
    @NonNull private LocalDate expiryDate;
}
