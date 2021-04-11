package ru.sber.atm.data;

import lombok.Data;
import lombok.NonNull;

/**
 * Класс представления информации о валюте
 */
@Data
public class Currency {
    @NonNull private int code;
    @NonNull private String name;
}