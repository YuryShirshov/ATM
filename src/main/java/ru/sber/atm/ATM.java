package ru.sber.atm;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.sber.atm.data.Account;
import ru.sber.atm.data.balance.Balance;
import ru.sber.atm.devices.cardprocessors.CardProcessor;
import ru.sber.atm.devices.cardreaders.CardReader;
import ru.sber.atm.devices.pinpads.PinPad;
import ru.sber.atm.enums.Command;
import ru.sber.atm.ui.UI;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ATM {
    @NonNull
    private final UI ui;
    @NonNull
    private final CardReader cardReader;
    @NonNull
    private final CardProcessor cardProcessor;
    @NonNull
    private final PinPad pinPad;

    public void Run() {
        // Показываем стартовую страницу
        ui.showStartPage();
        // Ждём карту
        cardReader.isCardInsert();
        // Считываем информацию с карты
        String rawData = cardReader.getRawData();
        // Получим информацию по номеру карты
        Account<Balance> account = cardProcessor.getAccountData(rawData);
        if (account == null) {
            ui.showCardNotFoundPage();
            ui.showRemoveCardPage();
            cardReader.removeCard();
            return;
        }
        // Запрашиваем ПИН-код
        ui.showPinPage();
        int pin = pinPad.getPinCode();
        if (!cardProcessor.checkPin(rawData, pin)) {
            // ПИН-код не прошел проверку, показываем ошибку и выходим
            ui.showWrongPinPage();
            ui.showRemoveCardPage();
            cardReader.removeCard();
            return;
        }
        // Получаем команду, которую ввёл пользователь
        Command command = ui.getCommand();
        if (command == Command.GET_BALANCE) {// Запрос баланса
            ui.showBalancePage(account.getNumber(), account.getBalance().getSum(), account.getBalance().getCurrency().getName());
        } else {// Остальные команды не реализованы
            ui.showUnsupportedCommandPage();
        }
        // Отдаем карту
        ui.showRemoveCardPage();
        cardReader.removeCard();
    }
}
