package ru.sber.atm.ui;

import ru.sber.atm.enums.Command;

import java.math.BigDecimal;

public interface UI {

    void showStartPage();

    void showPinPage();

    void showBalancePage(String accountNumber, BigDecimal balance, String currency);

    void showWrongPinPage();

    void showUnsupportedCommandPage();

    void showRemoveCardPage();

    void showCardNotFoundPage();

    Command getCommand();
}
