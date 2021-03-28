package ru.sber.atm.ui;

import ru.sber.atm.Command;

import java.math.BigDecimal;

public interface UI {

    void showStartPage();

    void showPinPage();

    void showBalancePage(BigDecimal balance);

    void showWrongPinPage();

    void showUnsupportedCommandPage();

    void showRemoveCardPage();

    Command getCommand();
}
