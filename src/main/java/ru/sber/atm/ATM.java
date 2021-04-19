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
import ru.sber.atm.enums.ValidationStatus;
import ru.sber.atm.ui.UI;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE)
public class ATM {
    @NonNull UI ui;
    @NonNull CardReader cardReader;
    @NonNull CardProcessor cardProcessor;
    @NonNull PinPad pinPad;

    public void Run() {
        // Показываем стартовую страницу
        ui.showStartPage();
        // Ждём карту
        cardReader.isCardInsert();
        // Считываем информацию с карты
        String rawData = cardReader.getRawData();
        // Запрашиваем ПИН-код
        ui.showPinPage();
        int pin = pinPad.getPinCode();
        // Валидируем полученную информацию
        ValidationStatus validationStatus = cardProcessor.validateCardData(rawData, pin);
        if (validationStatus != ValidationStatus.SUCCESS) {
            ui.showErrorPage(validationStatus.getDescription());
            ui.showRemoveCardPage();
            cardReader.removeCard();
            return;
        }
        // Получаем команду, которую ввёл пользователь
        Command command = ui.getCommand();
        if (command == Command.GET_BALANCE) {// Запрос баланса
            Account<Balance> account = cardProcessor.getAccountData(rawData);
            ui.showBalancePage(account.getNumber(), account.getBalance().getSum(), account.getBalance().getCurrency().name());
        } else {// Остальные команды не реализованы
            ui.showUnsupportedCommandPage();
        }
        // Отдаем карту
        ui.showRemoveCardPage();
        cardReader.removeCard();
    }
}
