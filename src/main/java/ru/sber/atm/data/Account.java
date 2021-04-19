package ru.sber.atm.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import ru.sber.atm.data.balance.Balance;

import javax.smartcardio.Card;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс представления информации о счёте
 */
@Data
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class Account<T extends Balance> {
    @NonNull String number;
    @NonNull T balance;
    @NonNull List<CardData> cardData;

    /**
     * Метод получения информации о карте по номеру
     */
    public CardData getCardDataByNumber(String number) {
        return cardData.stream().filter(card -> card.getNumber().equals(number)).findAny().orElse(null);
    }
}
