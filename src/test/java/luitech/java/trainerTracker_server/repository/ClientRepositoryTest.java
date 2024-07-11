package luitech.java.trainerTracker_server.repository;

import luitech.java.trainerTracker_server.model.Client;
import luitech.java.trainerTracker_server.model.ClientInfo;
import luitech.java.trainerTracker_server.model.Exercise;
import luitech.java.trainerTracker_server.model.Trainer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientRepositoryTest {

    @Autowired
            ClientRepository clientRepository;
    @Autowired
            ExerciseRepository exerciseRepository;
    @Autowired
            TrainerRepository trainerRepository;

    Client client;
    ClientInfo clientInfo;
    Exercise exercise;
    Trainer trainer;

    @BeforeEach
    void setUp() {

        trainer = new Trainer();
        trainer.setName("jorge");
        trainerRepository.save(trainer);

        exercise = new Exercise("roll back", "slow tempo", "back");
        exerciseRepository.save(exercise);

        clientInfo = new ClientInfo("michael edwards", "empty st.", 1234567, "me@example.com");
        client = new Client("michael1", "1234", "mon, wed, fri", clientInfo);
        client.setTrainer(trainer);
        client.getExerciseList().add(exercise);
        clientRepository.save(client);
        System.out.println("THIS IS CLIENT IN BEFORE EACH: "+client);

    }

    @AfterEach
    void tearDown() {
        clientRepository.deleteById(client.getId());
        trainerRepository.deleteById(trainer.getId());
        exerciseRepository.deleteById(exercise.getId());
    }

    @Test
    public void saveClient_Test() {
        Optional<Client> clientOptional = clientRepository.findById(client.getId());
        assertTrue(clientOptional.isPresent());
        assertEquals("michael edwards", clientOptional.get().getClientInfo().getName());
    }


}