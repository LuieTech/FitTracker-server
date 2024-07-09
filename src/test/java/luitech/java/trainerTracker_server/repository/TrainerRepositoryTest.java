package luitech.java.trainerTracker_server.repository;

import jakarta.persistence.OneToMany;
import luitech.java.trainerTracker_server.model.Client;
import luitech.java.trainerTracker_server.model.Exercise;
import luitech.java.trainerTracker_server.model.Trainer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrainerRepositoryTest {

    @Autowired
            TrainerRepository trainerRepository;
    @Autowired
            ExerciseRepository exerciseRepository;
    @Autowired
            ClientRepository clientRepository;

    Trainer trainer;
    Client client;
    List<Client> clientList = new ArrayList<>();

    Exercise exercise;
    List<Exercise> exerciseList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        trainer = new Trainer();
        trainer.setName("luie");
        trainer.setEmail("luie@example.com");
        trainer.setPhoneNumber(456789);
        trainerRepository.save(trainer);

        exercise = new Exercise();
        exercise.setName("pull down");
        exercise.setDescription("slow tempo");
        exercise.setBodyPart("back");
        exerciseRepository.save(exercise);
        exerciseList.add(exercise);

        client = new Client();
        client.setEmail("alex@example.com");
        client.setPassword("1234");
        client.setUsername("alex");
        client.setComment("Cardio 3x weekly");
        client.setTrainerId(trainer.getTrainerId());
        client.setExercises(exerciseList);
        clientRepository.save(client);
        clientList.add(client);

        trainer.setClients(clientList);
        trainer.setExercises(exerciseList);
        trainerRepository.save(trainer);
    }

    @AfterEach
    void tearDown() {
        trainerRepository.deleteById(trainer.getTrainerId());
        clientRepository.deleteById(client.getClientId());
        exerciseRepository.deleteById(exercise.getExerciseId());
    }

    @Test
    public void saveTrainer_repositoryTest(){
        Optional<Trainer> trainerOptional = trainerRepository.findById(trainer.getTrainerId());
        assertTrue(trainerOptional.isPresent());
        assertEquals("luie", trainerOptional.get().getName());
        System.out.println("This is the created trainer: "+ trainerOptional.get());
    }

    @Test
    public void saveTrainer_invalidId_test(){
        Optional<Trainer> trainerOptional = trainerRepository.findById(999);
        assertTrue(trainerOptional.isEmpty());

    }
}