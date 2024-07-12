package luitech.java.trainerTracker_server.service.impl;

import luitech.java.trainerTracker_server.controller.dto.ClientEmailDTO;
import luitech.java.trainerTracker_server.model.Client;
import luitech.java.trainerTracker_server.model.Trainer;
import luitech.java.trainerTracker_server.repository.ClientRepository;
import luitech.java.trainerTracker_server.service.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Integer id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if(clientOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client "+id+" not found");
        return clientOptional.get();
    }

    @Override
    public void saveClient(Client clientBody) {
        clientRepository.save(clientBody);
    }

    @Override
    public void updateClientEmail(String email, Integer id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if(clientOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client "+id+" not found");
        Client client = clientOptional.get();
        client.getClientInfo().setEmail(email);
        clientRepository.save(client);
    }

    @Override
    public void updateClientUsername(String username, Integer id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if(clientOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client "+id+" not found");
        Client client = clientOptional.get();
        client.setUsername(username);
        clientRepository.save(client);
    }

    @Override
    public void updateClientPassword(String password, Integer id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if(clientOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client "+id+" not found");
        Client client = clientOptional.get();
        client.setPassword(password);
        clientRepository.save(client);
    }

    @Override
    public void deleteClient(Integer id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client"+id+"not found");
        clientRepository.deleteById(id);
    }


}
