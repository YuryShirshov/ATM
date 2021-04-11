package ru.sber.atm.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * Класс представления информации о валюте
 */
@Data
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class Currency {
    @NonNull int code;
    @NonNull String name;
}