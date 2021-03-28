package ru.sber.atm.ui;

import ru.sber.atm.Command;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Класс обеспечивающий взаимодействие с пользователем (ввод/вывод информации)
 */
public class CustomUI implements UI {

    @Override
    public void showStartPage() {
        System.out.println("Вставьте карту");
    }

    @Override
    public void showPinPage() {
        System.out.print("Введите ПИН-код: ");
    }

    @Override
    public Command getCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер команды (1 - Снять наличные, 2 - Внести наличные, 3 - Оплатить услуги, 4 - Узнать баланс): ");
        int number = scanner.nextInt();
        try {
            return Command.values()[number];
        } catch (ArrayIndexOutOfBoundsException e) {
            return Command.UNKNOWN;
        }
    }

    @Override
    public void showBalancePage(BigDecimal balance) {
        System.out.println("Баланс карты: " + balance);
    }

    @Override
    public void showWrongPinPage() {
        System.out.println("Введен неверный ПИН-код");
    }

    @Override
    public void showUnsupportedCommandPage() {
        System.out.println("Выбранная команда не поддерживается");
    }

    @Override
    public void showRemoveCardPage() {
        System.out.println("Заберите карту");
    }
}
