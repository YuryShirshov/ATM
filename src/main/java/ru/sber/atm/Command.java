package ru.sber.atm;

public enum Command {
    UNKNOWN("Неизвестная команда"),
    GET_CASH("Снять наличные"),
    DEPOSIT_CASH("Внести наличные"),
    PAY_FOR_SERVICES("Оплатить услуги"),
    GET_BALANCE("Узнать баланс");

    private String description;

    Command(String description) {
        this.description = description;
    }
}
