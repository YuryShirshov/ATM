package ru.sber.server.processing.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.common.processing.dto.BalanceDTO;
import ru.sber.server.processing.entity.Account;
import ru.sber.server.processing.entity.Card;
import ru.sber.server.processing.entity.Client;
import ru.sber.server.processing.exception.AccountNotFoundException;
import ru.sber.server.processing.exception.CardNotFoundException;
import ru.sber.server.processing.exception.ClientNotFoundException;
import ru.sber.server.processing.repository.ClientCrudRepository;

/**
 * Класс обеспечивающий получение баланса
 */
@Service
@AllArgsConstructor
public class BalanceService {

    private final ClientCrudRepository clientCrudRepository;

    /**
     * Метод получения баланса по карте
     * @param clientId идентификатор клиента
     * @param accountId идентификатор счёта
     * @param pin пин-код карты
     * @return объект класса {@code BalanceDTO} с информацией о балансе
     */
    public BalanceDTO getBalance(Long clientId, Long accountId, int pin) {
        Client client = clientCrudRepository.findById(clientId)
                .orElseThrow(ClientNotFoundException::new);
        Account account = client.getAccounts().stream().filter(account1 -> account1.getId().equals(accountId)).findAny()
                .orElseThrow(AccountNotFoundException::new);
        Card card = account.getCards().stream().filter(card1 -> card1.getPin() == pin).findAny()
                .orElseThrow(CardNotFoundException::new);
        return new BalanceDTO(client.getName(), card.getNumber(), (long) account.getBalance(), account.getCurrency());
    }
}
