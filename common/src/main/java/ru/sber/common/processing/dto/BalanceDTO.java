package ru.sber.common.processing.dto;

import lombok.Value;

@Value
public class BalanceDTO {
    String clientName;
    String cardNumber;
    Long sum;
    String currency;
}
