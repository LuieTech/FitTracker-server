package luitech.java.trainerTracker_server.service.impl;

import luitech.java.trainerTracker_server.model.Client;
import luitech.java.trainerTracker_server.model.Trainer;
import luitech.java.trainerTracker_server.repository.ClientRepository;
import luitech.java.trainerTracker_server.repository.TrainerRepository;
import luitech.java.trainerTracker_server.service.interfaces.ITrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService implements ITrainerService {

    @Autowired
    TrainerRepository trainerRepository;
    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    public Trainer getTrainerById(Integer id) {
        Optional<Trainer> trainerOptional = trainerRepository.findById(id);
        if(trainerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer "+id+" Nof found");
        return trainerOptional.get();
    }

    @Override
    public void saveTrainer(Trainer trainerBody) {
        trainerRepository.save(trainerBody);
    }

    @Override
    public void updateTrainer(Trainer trainerInfo, Integer id) {
        Optional<Trainer> trainerOptional = trainerRepository.findById(id);
        if(trainerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer "+id+" not found");
        trainerRepository.save(trainerInfo);
    }

    @Override
    public List<Client> getAllClientsByTrainerId(Integer trainerId) {
        return clientRepository.findAllByTrainerId(trainerId);
    }


}
