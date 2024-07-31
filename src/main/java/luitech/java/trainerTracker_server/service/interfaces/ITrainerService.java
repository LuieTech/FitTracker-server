package luitech.java.trainerTracker_server.service.interfaces;

import luitech.java.trainerTracker_server.model.Client;
import luitech.java.trainerTracker_server.model.Exercise;
import luitech.java.trainerTracker_server.model.Trainer;

import java.util.List;

public interface ITrainerService {
    List<Trainer> getAllTrainers();

    Trainer getTrainerById(Integer id);

    void saveTrainer(Trainer trainerBody);

    void updateTrainer(Trainer trainerInfo, Integer id);


    List<Client> getAllClientsByTrainerId(Integer trainerId);

    void deleteTrainer(Integer id);
}