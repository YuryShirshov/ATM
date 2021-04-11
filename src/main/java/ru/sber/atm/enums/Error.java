package ru.sber.atm.enums;

public enum Error {
    NO_ERROR("Ошибок нет"),
    HOLDER_NOT_FOUND("Клиент не найден"),
    INCORRECT_PIN("Неверный ПИН-код"),
    DATE_EXPIRED("Срок действия карты истек");

    public String getDescription() {
        return description;
    }

    private final String description;

    Error(String description) {
        this.description = description;
    }
}
