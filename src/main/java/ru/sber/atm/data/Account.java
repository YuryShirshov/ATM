package ru.sber.atm.data;

import lombok.Data;
import lombok.NonNull;
import ru.sber.atm.data.balance.Balance;

import java.util.List;

/**
 * Класс представления информации о счёте
 */
@Data
public class Account<T extends Balance> {
    @NonNull private String number;
    @NonNull private T balance;
    @NonNull private List<CardData> cardData;

    /**
     * Метод получения информации о карте по номеру
     */
    public CardData getCardDataByNumber(String number) {
        return cardData.stream().filter(card -> card.getNumber().equals(number)).findAny().orElse(null);
    }
}
