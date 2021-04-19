package ru.sber.atm.enums;

public enum ValidationStatus {
    SUCCESS("Ошибок нет"),
    HOLDER_NOT_FOUND("Клиент не найден"),
    INCORRECT_PIN("Неверный ПИН-код"),
    DATE_EXPIRED("Срок действия карты истек");

    public String getDescription() {
        return description;
    }

    private final String description;

    ValidationStatus(String description) {
        this.description = description;
    }
}
