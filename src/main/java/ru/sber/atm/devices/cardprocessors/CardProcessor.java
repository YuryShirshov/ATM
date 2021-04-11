package ru.sber.atm.devices.cardprocessors;

import ru.sber.atm.data.Account;
import ru.sber.atm.data.balance.Balance;

public interface CardProcessor {

    Account<Balance> getAccountData(String cardNum);

    Boolean checkPin(String rawData, int pin);
}
