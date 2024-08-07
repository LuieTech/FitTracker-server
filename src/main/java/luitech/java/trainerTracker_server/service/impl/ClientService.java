package luitech.java.trainerTracker_server.service.impl;

import jakarta.transaction.Transactional;
import luitech.java.trainerTracker_server.model.Client;
import luitech.java.trainerTracker_server.model.Exercise;
import luitech.java.trainerTracker_server.repository.ClientRepository;
import luitech.java.trainerTracker_server.repository.ExerciseRepository;
import luitech.java.trainerTracker_server.service.interfaces.IClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientService.class);
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ExerciseRepository exerciseRepository;

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
        client.setEmail(email);
        clientRepository.save(client);
    }

    @Override
    @Transactional
    public void deleteClient(Integer id) {

//        Optional<Client> clientOptional = clientRepository.findById(id);
//        if(clientOptional.isPresent()){
//            exerciseRepository.deleteByClientId(id);
//            clientRepository.deleteById(id);
//        } else {
//            throw new RuntimeException("Client not found");
//        }

        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client"+id+"not found");
        clientRepository.deleteById(id);
    }

    @Override
    public List<Exercise> getAllExercisesByClientId(Integer clientId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if(clientOptional.isPresent()){
            return exerciseRepository.findAllByClientId(clientId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client "+clientId+" not found");
        }
    }

}
