package ru.sber.atm.devices.pinpads;

import java.util.Scanner;

/**
 * Класс обеспечивающий работу с пин падом
 */
public class CustomPinPad implements PinPad {
    @Override
    public int getPinCode() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
