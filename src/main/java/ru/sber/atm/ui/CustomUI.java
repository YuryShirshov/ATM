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
        System.out.println("Введите номер команды: "
                + System.lineSeparator()
                + Command.GET_CASH.getDescription()
                + System.lineSeparator()
                + Command.DEPOSIT_CASH.getDescription()
                + System.lineSeparator()
                + Command.PAY_FOR_SERVICES.getDescription()
                + System.lineSeparator()
                + Command.GET_BALANCE.getDescription()
        );
        int number = scanner.nextInt();
        if (number < 1 || number > Command.values().length) {
            return Command.UNKNOWN;
        } else {
            return Command.values()[number];
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
