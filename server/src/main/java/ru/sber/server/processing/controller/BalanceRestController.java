package ru.sber.server.processing.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.common.processing.dto.BalanceDTO;
import ru.sber.server.processing.service.BalanceService;

@RestController
@AllArgsConstructor
@Slf4j
public class BalanceRestController {

    private final BalanceService balanceService;

    @GetMapping("balance")
    public BalanceDTO getBalance(@RequestParam("client_id") Long clientId,
                                 @RequestParam("account_id") Long accountId,
                                 @RequestParam("pin") int pin) {
        log.info("getBalance client_id={} account_id={} pin={}", clientId, accountId, pin);
        return balanceService.getBalance(clientId, accountId, pin);

    }
}
