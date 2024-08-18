package luitech.java.trainerTracker_server.service.impl;

import lombok.RequiredArgsConstructor;
import luitech.java.trainerTracker_server.model.Client;
import luitech.java.trainerTracker_server.model.Trainer;
import luitech.java.trainerTracker_server.repository.ClientRepository;
import luitech.java.trainerTracker_server.repository.ExerciseRepository;
import luitech.java.trainerTracker_server.repository.TrainerRepository;
import luitech.java.trainerTracker_server.service.interfaces.ITrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainerService implements ITrainerService, UserDetailsService {

    private final TrainerRepository trainerRepository;
    private final ClientRepository clientRepository;
    private final ExerciseRepository exerciseRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return trainerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    public Trainer getTrainerById(Integer id) {
        Optional<Trainer> trainerOptional = trainerRepository.findById(Long.valueOf(id));
        if (trainerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer " + id + " Not found");
        return trainerOptional.get();
    }

    @Override
    public void saveTrainer(Trainer trainerBody) {
        trainerRepository.save(trainerBody);
    }

    @Override
    public void updateTrainer(Trainer trainerInfo, Integer id) {
        Optional<Trainer> trainerOptional = trainerRepository.findById(Long.valueOf(id));
        if (trainerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer " + id + " not found");
        trainerRepository.save(trainerInfo);
    }

    @Override
    public List<Client> getAllClientsByTrainerId(Integer trainerId) {
        return clientRepository.findAllByTrainerId(trainerId);
    }

    @Override
    public void deleteTrainer(Integer trainerId) {
        Optional<Trainer> trainerOptional = trainerRepository.findById(Long.valueOf(trainerId));
        if (trainerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer" + trainerId + "not found");
        List<Client> clients = clientRepository.findAllByTrainerId(trainerId);
        clientRepository.deleteAll(clients);
        trainerRepository.deleteById(Long.valueOf(trainerId));
    }
}
