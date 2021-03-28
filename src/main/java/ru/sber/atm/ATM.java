package ru.sber.atm;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.sber.atm.data.CardData;
import ru.sber.atm.devices.cardprocessors.CardProcessor;
import ru.sber.atm.devices.cardreaders.CardReader;
import ru.sber.atm.devices.pinpads.PinPad;
import ru.sber.atm.ui.UI;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ATM {
    @NonNull
    private UI ui;
    @NonNull
    private CardReader cardReader;
    @NonNull
    private CardProcessor cardProcessor;
    @NonNull
    private PinPad pinPad;

    public void Run() {
        // Показываем стартовую страницу
        ui.showStartPage();
        // Ждём карту
        cardReader.isCardInsert();
        // Считываем информацию с карты
        String rawData = cardReader.getRawData();
        // Запрашиваем ПИН-код
        ui.showPinPage();
        String pin = pinPad.getPinCode();
        if (!cardProcessor.checkPin(rawData, pin)) {
            // ПИН-код не прошел проверку, показываем ошибку и выходим
            ui.showWrongPinPage();
            return;
        }
        // Получаем команду, которую ввёл пользователь
        Command command = ui.getCommand();
        if (command == Command.GET_BALANCE) {// Запрос баланса
            CardData cardData = cardProcessor.getCardData(rawData);
            ui.showBalancePage(cardData.getBalance());
        } else {// Остальные команды не реализованы
            ui.showUnsupportedCommandPage();
        }
        // Отдаем карту
        ui.showRemoveCardPage();
        cardReader.removeCard();
    }
}
