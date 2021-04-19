package ru.sber.atm.devices.cardprocessors;

import ru.sber.atm.data.*;
import ru.sber.atm.data.balance.*;
import ru.sber.atm.enums.Currency;
import ru.sber.atm.enums.ValidationStatus;

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
        accounts.add(new Account<>("9999 9999 9999 9999", new BalanceWithNegativeValue(BigDecimal.valueOf(1000), Currency.RUB), cardData));
        accounts.add(new Account<>("8888 8888 8888 8888", new BalanceWithoutNegativeValue(BigDecimal.valueOf(2000), Currency.RUB), new ArrayList<>()));
        clients.add(new Client<>(1, "Client #1", 25, accounts));
    }

    /**
     * Метод получения информации о счёте по номеру карты
     */
    @Override
    public Account<Balance> getAccountData(String cardNum) {
        Client<Balance> client = getClientData(cardNum);
        if (client != null) return client.getAccountByNumber(cardNum);
        return null;
    }

    /**
     * Метод валидации полученной информации по карте
     */
    @Override
    public ValidationStatus validateCardData(String cardNum, int pin) {
        Client<Balance> client = getClientData(cardNum);
        if (client == null) {
            return ValidationStatus.HOLDER_NOT_FOUND;
        }
        CardData cardData = client.getAccountByNumber(cardNum).getCardDataByNumber(cardNum);
        if (cardData.getPin() != pin) {
            return ValidationStatus.INCORRECT_PIN;
        }
        if (cardData.getExpiryDate().compareTo(LocalDate.now()) < 0) {
            return ValidationStatus.DATE_EXPIRED;
        }
        return ValidationStatus.SUCCESS;
    }

    private Client<Balance> getClientData(String cardNum) {
        return clients.stream().filter(x -> x.getAccountByNumber(cardNum) != null).findAny().orElse(null);
    }
}
