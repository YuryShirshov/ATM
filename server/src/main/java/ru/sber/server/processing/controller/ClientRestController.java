package ru.sber.server.processing.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sber.common.processing.dto.ClientDTO;
import ru.sber.server.processing.service.ClientService;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientRestController {

    private final ClientService clientService;

    @GetMapping("/clients")
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping("/clients")
    ClientDTO addClient(@RequestBody ClientDTO newClient) {
        return clientService.addClient(newClient);
    }

    @GetMapping("clients/{clientId}")
    public ClientDTO getClient(@PathVariable Long clientId) {
        return clientService.getClient(clientId);
    }

    @PutMapping("/clients/{clientId}")
    ClientDTO replaceClient(@RequestBody ClientDTO newClient, @PathVariable Long clientId) {
        return clientService.replaceClient(newClient, clientId);
    }

    @DeleteMapping("clients/{clientId}")
    void deleteClient(@PathVariable Long clientId) {
        clientService.deleteClientById(clientId);
    }
}
