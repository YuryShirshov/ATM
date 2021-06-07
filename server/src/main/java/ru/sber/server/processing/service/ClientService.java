package ru.sber.server.processing.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.common.processing.dto.AccountDTO;
import ru.sber.common.processing.dto.CardDTO;
import ru.sber.common.processing.dto.ClientDTO;
import ru.sber.server.processing.entity.Account;
import ru.sber.server.processing.entity.Card;
import ru.sber.server.processing.entity.Client;
import ru.sber.server.processing.exception.ClientNotFoundException;
import ru.sber.server.processing.repository.ClientCrudRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Класс реализующий CRUD операции с клиентами
 */
@Service
@AllArgsConstructor
public class ClientService {

    private final ClientCrudRepository clientCrudRepository;

    /**
     * Возвращает клиента по его идентификатору
     * @param id идентификатор клиента
     * @return {@code ClientDTO} информация о клиенте
     */
    public ClientDTO getClient(Long id) {
        Client client = clientCrudRepository.findById(id)
                .orElseThrow(ClientNotFoundException::new);
        return getClientDTO(client);
    }

    /**
     * Возвращает список всех клиентов
     * @return {@code List<ClientDTO>} список всех клиентов
     */
    public List<ClientDTO> getAllClients() {
        Iterable<Client> clientIterable = clientCrudRepository.findAll();
        List<ClientDTO> clientDTOLists = new ArrayList<>();

        clientIterable.forEach(
                client -> clientDTOLists.add(getClientDTO(client))
        );
        return clientDTOLists;
    }

    /**
     * Добавление нового клиента
     * @param clientDTO информация о новом клиенте
     * @return {@code ClientDTO} добавленная запись а клиенте
     */
    public ClientDTO addClient(ClientDTO clientDTO) {
        Client client = getClientEntity(clientDTO);
        Client savedClient = clientCrudRepository.save(client);
        return getClientDTO(savedClient);
    }

    /**
     * Редактирование существующего клиента (либо добавление в случае его отсутствия)
     * @param clientDTO новая информация о клиенте
     * @param id идентификатор клиента
     * @return {@code ClientDTO} информация о клиенте
     */
    public ClientDTO replaceClient(ClientDTO clientDTO, Long id) {
        Client client = getClientEntity(clientDTO);
        client.setId(id);
        Client savedClient = clientCrudRepository.save(client);
        return getClientDTO(savedClient);
    }

    /**
     * Удаление клиента
     * @param id идентификатор клиента
     */
    public void deleteClientById(Long id) {
        clientCrudRepository.deleteById(id);
    }

    /**
     * Трансформация {@code Client} объекта в {@code ClientDTO}
     */
    private ClientDTO getClientDTO(Client client) {
        Set<AccountDTO> accountDTOList = getAllAccountsByClient(client);
        return new ClientDTO(client.getId().intValue(), client.getName(), client.getAge(), accountDTOList);
    }

    /**
     * Получение списка счетов {@code Set<AccountDTO>} из объекта класса {@code Client}
     * @param client информация о клиенте
     * @return {@code Set<AccountDTO>} список счетов
     * @see #getClientDTO(Client)
     */
    private Set<AccountDTO> getAllAccountsByClient(Client client) {
        Set<AccountDTO> accountDTOSet = new HashSet<>();

        client.getAccounts().forEach(
                account -> {
                    Set<CardDTO> cardDTOSet = getAllCardsByAccount(account);
                    accountDTOSet.add(new AccountDTO(account.getId().intValue(),
                            account.getNumber(), account.getBalance(), account.getCurrency(), cardDTOSet));
                }
        );
        return accountDTOSet;
    }

    /**
     * Получение списка карт {@code Set<CardDTO>} из объекта класса {@code Account}
     * @param account информация о счёте
     * @return {@code Set<CardDTO>} список карт
     * @see #getAllAccountsByClient(Client) 
     */
    private Set<CardDTO> getAllCardsByAccount(Account account) {
        Set<CardDTO> cardDTOSet = new HashSet<>();

        account.getCards().forEach(
                card -> cardDTOSet.add(new CardDTO(card.getId().intValue(), card.getNumber(), card.getPin(), card.getCvc2()))
        );
        return cardDTOSet;
    }

    /**
     * Трансформация {@code ClientDTO} объекта в {@code Client}
     */
    private Client getClientEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId((long) clientDTO.getClientId());
        client.setName(clientDTO.getName());
        client.setAge(clientDTO.getAge());
        Set<Account> accounts = getAllAccountsByClientDTO(clientDTO, client);
        client.setAccounts(accounts);
        return client;
    }

    /**
     * Получение списка счетов {@code Set<Account>} из объекта класса {@code ClientDTO}
     * @param clientDTO информация о клиенте в формате {@code ClientDTO}
     * @param client информация о клиенте в формате {@code Client} для связи со счетами
     * @return {@code Set<Account>} список счетов, привязанных к клиенту
     * @see #getClientEntity(ClientDTO)
     */
    private Set<Account> getAllAccountsByClientDTO(ClientDTO clientDTO, Client client) {
        Set<Account> accounts = new HashSet<>();

        if (clientDTO.getAccountDTOList() != null) {
            clientDTO.getAccountDTOList().forEach(
                    accountDTO -> {
                        Account account = new Account();
                        account.setClient_id(client);
                        account.setId((long) accountDTO.getAccountId());
                        account.setNumber(accountDTO.getNumber());
                        account.setBalance(accountDTO.getBalance());
                        account.setCurrency(accountDTO.getCurrency());
                        Set<Card> cards = getAllCardsByAccountDTO(accountDTO, account);
                        account.setCards(cards);
                        accounts.add(account);
                    }
            );
        }
        return accounts;
    }

    /**
     * Получение списка карт {@code Set<Card>} из объекта класса {@code AccountDTO}
     * @param accountDTO информация о счёте в формате {@code ClientDTO}
     * @param account информация о счёте в формате {@code Account} для связи с картами
     * @return {@code Set<Card>} список карт, привязанных к счёту
     * @see #getAllAccountsByClientDTO(ClientDTO, Client) 
     */
    private Set<Card> getAllCardsByAccountDTO(AccountDTO accountDTO, Account account) {
        Set<Card> cards = new HashSet<>();

        if (accountDTO.getCardDTOList() != null) {
            accountDTO.getCardDTOList().forEach(
                    cardDTO -> {
                        Card card = new Card();
                        card.setAccount_id(account);
                        card.setId((long) cardDTO.getCardId());
                        card.setNumber(cardDTO.getNumber());
                        card.setPin(cardDTO.getPin());
                        card.setCvc2(cardDTO.getCvc2());
                        cards.add(card);
                    }
            );
        }
        return cards;
    }

}
