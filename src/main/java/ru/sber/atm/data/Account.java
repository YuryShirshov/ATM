package ru.sber.atm.data;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.sber.atm.data.balance.Balance;

import java.util.List;
import java.util.Optional;

/**
 * Класс представления информации о счёте
 */
@Data
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class Account<T extends Balance> {
    @NonNull String number;
    @NonNull T balance;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @NonNull List<CardData> cardData;

    /**
     * Метод получения информации о карте по номеру
     */
    public Optional<CardData> getCardDataByNumber(String number) {
        return cardData.stream().filter(card -> card.getNumber().equals(number)).findAny();
    }
}
