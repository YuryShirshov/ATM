package ru.sber.atm.devices.cardprocessors;

import ru.sber.atm.data.Account;
import ru.sber.atm.data.balance.Balance;
import ru.sber.atm.enums.ValidationStatus;

public interface CardProcessor {

    Account<Balance> getAccountData(String cardNum);

    ValidationStatus validateCardData(String cardNum, int pin);
}
