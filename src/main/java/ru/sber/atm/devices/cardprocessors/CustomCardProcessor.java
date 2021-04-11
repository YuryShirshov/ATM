package ru.sber.atm.devices.cardprocessors;

import ru.sber.atm.data.CardData;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Класс обеспечивающий процессинг взаимодействия с банковской частью
 */
public class CustomCardProcessor implements CardProcessor {

    /**
     * Метод проверки ПИН-кода
     */
    @Override
    public Boolean checkPin(String rawData, String pin) {
        return pin.equals("1234");
    }

    /**
     * Метод получения информации о карте по номеру
     */
    @Override
    public CardData getCardData(String cardNum) {
        CardData cardData = new CardData();
        cardData.setNumber("1111 1111 1111 1111");
        cardData.setHolder("Holder Name");
        cardData.setCvc2(111);
        cardData.setExpiryDate(LocalDate.of(2023, 1, 1));
        cardData.setBalance(BigDecimal.valueOf(1000.00));
        return cardData;
    }
}
