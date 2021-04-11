package ru.sber.atm.devices.cardprocessors;

import ru.sber.atm.data.*;
import ru.sber.atm.data.balance.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс обеспечивающий процессинг взаимодействия с банковской частью
 */
public class CustomCardProcessor implements CardProcessor {

    private final List<Client<Balance>> clients = new ArrayList<>();

    {
        List<CardData> cardData = new ArrayList<>();
        cardData.add(new CardData(1234, "1111 1111 1111 1111", 111, LocalDate.of(2023, 1, 1)));
        cardData.add(new CardData(4321, "1111 1111 1111 1112", 222, LocalDate.of(2020, 1, 1)));
        List<Account<Balance>> accounts = new ArrayList<>();
        accounts.add(new Account<>("9999 9999 9999 9999", new BalanceWithNegativeValue(BigDecimal.valueOf(1000), new Currency(810, "RUB")), cardData));
        clients.add(new Client<>(1, "Client #1", 25, accounts));
    }

    /**
     * Метод получения информации о счёте по номеру карты
     */
    @Override
    public Account<Balance> getAccountData(String cardNum) {
        Client<Balance> client = clients.stream().filter(x -> x.getAccountByNumber(cardNum) != null).findAny().orElse(null);
        if (client != null) return client.getAccountByNumber(cardNum);
        return null;
    }

    /**
     * Метод проверки ПИН-кода
     */
    @Override
    public Boolean checkPin(String rawData, int pin) {
        Account<Balance> account = getAccountData(rawData);
        if (account != null) {
            CardData cardData = account.getCardDataByNumber(rawData);
            if (cardData != null) {
                return cardData.getPin() == pin;
            }
        }
        return false;
    }
}
