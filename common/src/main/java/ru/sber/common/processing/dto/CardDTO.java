package ru.sber.common.processing.dto;

import lombok.Value;

@Value
public class CardDTO {
    int cardId;
    String number;
    int pin;
    int cvc2;
}
