package ru.sber.atm.enums;

public enum Currency {
    RUB("Российский рубль"),
    USD("Доллар США");

    public String getDescription() {
        return description;
    }

    private final String description;

    Currency(String description) {
        this.description = description;
    }
}
