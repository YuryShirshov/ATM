package ru.sber.atm.devices.cardreaders;

import java.util.Scanner;

/**
 * Класс обеспечивающий чтение информации с карты
 */
public class CustomCardReader implements CardReader {

    @Override
    public Boolean isCardInsert() {
        return true;
    }

    @Override
    public String getRawData() {
        return "1111 1111 1111 1111";
    }

    @Override
    public void removeCard() {

    }
}
