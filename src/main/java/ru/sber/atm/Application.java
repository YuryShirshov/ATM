package ru.sber.atm;

import ru.sber.atm.devices.cardprocessors.CustomCardProcessor;
import ru.sber.atm.devices.cardreaders.CustomCardReader;
import ru.sber.atm.devices.pinpads.CustomPinPad;
import ru.sber.atm.ui.CustomUI;

public class Application {
    public static void main(String[] args) {
        // Создаём банкмат с дефолтными параметрами
        ATM atm = new ATM(new CustomUI(), new CustomCardReader(), new CustomCardProcessor(), new CustomPinPad());
        // Включаем
        atm.Run();
    }
}
