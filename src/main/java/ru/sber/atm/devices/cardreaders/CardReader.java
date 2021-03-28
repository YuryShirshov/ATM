package ru.sber.atm.devices.cardreaders;

public interface CardReader {

    Boolean isCardInsert();

    String getRawData();

    void removeCard();
}
