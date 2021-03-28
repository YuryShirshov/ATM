package ru.sber.atm.devices.cardprocessors;

import ru.sber.atm.data.CardData;

public interface CardProcessor {

    Boolean checkPin(String rawData, String pin);

    CardData getCardData(String cardNum);
}
