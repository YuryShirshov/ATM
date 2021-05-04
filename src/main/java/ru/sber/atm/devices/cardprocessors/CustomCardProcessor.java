package ru.sber.atm.devices.cardprocessors;

import ru.sber.atm.data.*;
import ru.sber.atm.data.balance.*;
import ru.sber.atm.enums.Currency;
import ru.sber.atm.enums.ValidationStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Account<Balance>> getAccountData(String cardNum) {
        Optional<Client<Balance>> clientOptional = getClientData(cardNum);
        if (clientOptional.isPresent()) {
            return clientOptional.get().getAccountByNumber(cardNum);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Метод валидации полученной информации по карте
     */
    @Override
    public ValidationStatus validateCardData(String cardNum, int pin) {
        Optional<Client<Balance>> clientOptional = getClientData(cardNum);
        if (!clientOptional.isPresent()) {
            return ValidationStatus.HOLDER_NOT_FOUND;
        }
        Optional<Account<Balance>> accountOptional = clientOptional.get().getAccountByNumber(cardNum);
        if (!accountOptional.isPresent()) {
            return ValidationStatus.ACCOUNT_NOT_FOUND;
        }
        Optional<CardData> cardDataOptional = accountOptional.get().getCardDataByNumber(cardNum);
        if (!cardDataOptional.isPresent()) {
            return ValidationStatus.CARD_NOT_FOUND;
        }
        CardData cardData = cardDataOptional.get();
        if (cardData.getPin() != pin) {
            return ValidationStatus.INCORRECT_PIN;
        }
        if (cardData.getExpiryDate().compareTo(LocalDate.now()) < 0) {
            return ValidationStatus.DATE_EXPIRED;
        }
        return ValidationStatus.SUCCESS;
    }

    private Optional<Client<Balance>> getClientData(String cardNum) {
        return clients.stream().filter(x -> x.getAccountByNumber(cardNum).isPresent()).findAny();
    }
}
