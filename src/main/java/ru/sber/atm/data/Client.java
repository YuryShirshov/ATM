package ru.sber.atm.data;

import lombok.Data;
import lombok.NonNull;
import ru.sber.atm.data.balance.Balance;

import java.util.List;

/**
 * Класс представления информации о клиенте
 */
@Data
public class Client<T extends Balance> {
    @NonNull private int number;
    @NonNull private String name;
    @NonNull private int age;
    @NonNull private List<Account<T>> accounts;

    /**
     * Метод получения информации о счёте по номеру карты
     */
    public Account<T> getAccountByNumber(String number) {
        return accounts.stream().filter(account -> account.getCardDataByNumber(number) != null).findAny().orElse(null);
    }
}
