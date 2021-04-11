package ru.sber.atm.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/**
 * Класс представления информации о карте
 */
@Data
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class CardData {
    @NonNull int pin;
    @NonNull String number;
    @NonNull int cvc2;
    @NonNull LocalDate expiryDate;
}
