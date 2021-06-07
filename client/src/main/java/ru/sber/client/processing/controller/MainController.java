package ru.sber.client.processing.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sber.client.processing.service.BalanceService;
import ru.sber.client.processing.service.CardReaderService;
import ru.sber.client.processing.service.PinPadService;
import ru.sber.common.processing.dto.BalanceDTO;

@Controller
@AllArgsConstructor
@Log
public class MainController {

    private final BalanceService balanceService;
    private final CardReaderService cardReaderService;
    private final PinPadService pinPadService;

    @GetMapping("balance")
    public String pageBalance(Model model) {
        BalanceDTO balanceDTO = balanceService.getBalance(
                cardReaderService.getClientId(),
                cardReaderService.getAccountId(),
                pinPadService.getPin()
        );
        model.addAttribute("balance", balanceDTO);
        return "pageBalance";
    }
}
