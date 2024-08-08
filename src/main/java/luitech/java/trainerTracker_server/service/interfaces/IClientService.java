package luitech.java.trainerTracker_server.service.interfaces;

import luitech.java.trainerTracker_server.controller.dto.ClientCommentDTO;
import luitech.java.trainerTracker_server.model.Client;
import luitech.java.trainerTracker_server.model.Exercise;

import java.util.List;

public interface IClientService {
    List<Client> getAllClients();

    Client getClientById(Integer id);

    void saveClient(Client clientBody);

    void updateClientEmail(String email, Integer id);

    void deleteClient(Integer clientId);

    List<Exercise> getAllExercisesByClientId(Integer clientId);

    void updateClientComment(String comment, Integer id);
}
