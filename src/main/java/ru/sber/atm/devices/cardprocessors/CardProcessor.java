package ru.sber.atm.devices.cardprocessors;

import ru.sber.atm.data.Account;
import ru.sber.atm.data.balance.Balance;
import ru.sber.atm.enums.Error;

public interface CardProcessor {

    Account<Balance> getAccountData(String cardNum);

    Error validateCardData(String cardNum, int pin);
}
