package ru.sber.client.processing.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс обеспечивающий чтение информации с карты.
 * Эмулирует чтение информации с карты путём считывания параметров с файла card.data
 */
@Service
@AllArgsConstructor
public class CardReaderService {

    static final String CARD_FILE_PATH = "client/src/main/resources/card.data";

    /**
     * Получение идентификатора клиента
     * @return {@code Long} идентификатор клиента
     */
    public Long getClientId() {
        return getParamByIndex(0);
    }

    /**
     * Получение идентификатора счёта
     * @return {@code Long} идентификатор счёта
     */
    public Long getAccountId() {
        return getParamByIndex(1);
    }

    /**
     * Получение параметра из файла по индексу
     * @param Index индекс параметра
     * @return {@code Long} значение параметра
     */
    private Long getParamByIndex(int Index) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CARD_FILE_PATH))) {
            long[] params = bufferedReader.lines().mapToLong(Long::parseLong).toArray();
            return params[Index];
        } catch (IOException e) {
            e.printStackTrace();
            return 0L;
        }
    }
}
