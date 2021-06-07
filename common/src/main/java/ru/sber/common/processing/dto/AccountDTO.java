package ru.sber.common.processing.dto;

import lombok.Value;

import java.util.Set;

@Value
public class AccountDTO {
    int accountId;
    String number;
    int balance;
    String currency;
    Set<CardDTO> cardDTOList;
}
