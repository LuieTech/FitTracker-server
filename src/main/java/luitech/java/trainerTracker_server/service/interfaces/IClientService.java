package luitech.java.trainerTracker_server.service.interfaces;

import luitech.java.trainerTracker_server.model.Client;
import luitech.java.trainerTracker_server.model.Exercise;

import java.util.List;

public interface IClientService {
    List<Client> getAllClients();

    Client getClientById(Integer id);

    void saveClient(Client clientBody);

    void updateClientEmail(String email, Integer id);

    void updateClientUsername(String username, Integer id);

    void updateClientPassword(String password, Integer id);

    void deleteClient(Integer clientId);

//    void addExerciseToClient(Integer clientId, Integer exerciseId);

    List<Exercise> getAllExercisesByClientId(Integer clientId);

}
