package ru.sber.atm.enums;

public enum Command {
    UNKNOWN("Неизвестная команда"),
    GET_CASH("1 - Снять наличные"),
    DEPOSIT_CASH("2 - Внести наличные"),
    PAY_FOR_SERVICES("3 - Оплатить услуги"),
    GET_BALANCE("4 - Узнать баланс");

    public String getDescription() {
        return description;
    }

    private final String description;

    Command(String description) {
        this.description = description;
    }
}
