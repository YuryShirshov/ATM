package ru.sber.client.processing.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sber.client.processing.exception.BalanceNotFoundException;
import ru.sber.common.processing.dto.BalanceDTO;

/**
 * Класс обеспечивающий связь с сервером и получение баланса.
 * Обращается к сервису на удалённой машине
 */
@Service
@AllArgsConstructor
public class BalanceService {

    static final String URL_BALANCE = "http://localhost:8080/balance?client_id=%d&account_id=%d&pin=%d";

    /**
     * Получение баланса
     * @param clientId идентифактор клиента
     * @param accountId идентификатор счёта
     * @param pin пин-код
     * @return {@code BalanceDTO} информация о балансе
     */
    public BalanceDTO getBalance(Long clientId, Long accountId, int pin) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(String.format(URL_BALANCE, clientId, accountId, pin), BalanceDTO.class);
        } catch (RuntimeException e) {
            throw new BalanceNotFoundException();
        }
    }
}
