package ru.sber.atm.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import ru.sber.atm.data.balance.Balance;

import java.util.List;

/**
 * Класс представления информации о клиенте
 */
@Data
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class Client<T extends Balance> {
    @NonNull int number;
    @NonNull String name;
    @NonNull int age;
    @NonNull List<Account<T>> accounts;

    /**
     * Метод получения информации о счёте по номеру карты
     */
    public Account<T> getAccountByNumber(String number) {
        return accounts.stream().filter(account -> account.getCardDataByNumber(number) != null).findAny().orElse(null);
    }
}
