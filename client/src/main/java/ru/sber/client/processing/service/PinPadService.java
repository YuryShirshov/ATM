package ru.sber.client.processing.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс обеспечивающий работу с пин падом.
 * Эмулирует ввод пин-кода чтением его значения из файла pinpad.data
 */
@Service
@AllArgsConstructor
public class PinPadService {

    static final String PIN_FILE_PATH = "client/src/main/resources/pinpad.data";

    /**
     * Получение пин-кода
     * @return {@code int} пин-код карты
     */
    public int getPin() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PIN_FILE_PATH))) {
            return Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
