package ru.sber.common.processing.dto;

import lombok.Value;

import java.util.Set;

@Value
public class ClientDTO {
    int clientId;
    String name;
    int age;
    Set<AccountDTO> accountDTOList;
}
