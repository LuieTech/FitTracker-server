package luitech.java.trainerTracker_server.service.interfaces;

import luitech.java.trainerTracker_server.controller.dto.ClientEmailDTO;
import luitech.java.trainerTracker_server.model.Client;

import java.util.List;

public interface IClientService {
    List<Client> getAllClients();

    Client getClientById(Integer id);

    void saveClient(Client clientBody);

    void updateClientEmail(String email, Integer id);

    void updateClientUsername(String username, Integer id);

    void updateClientPassword(String password, Integer id);

    void deleteClient(Integer clientId);
}
