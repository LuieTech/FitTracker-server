package luitech.java.trainerTracker_server.repository;

import luitech.java.trainerTracker_server.model.Client;
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
class TrainerRepositoryTest {

    @Autowired
            TrainerRepository trainerRepository;
    @Autowired
            ExerciseRepository exerciseRepository;
    @Autowired
            ClientRepository clientRepository;

    Trainer trainer;
    Client client;
    Exercise exercise;

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
//
//        client = new Client();
//        client.setName("alexander rubio");
//        client.setEmail("alex@example.com");
//        client.setPassword("1234");
//        client.setUsername("alex");
//        client.setComment("Cardio 3x weekly");
//        client.setTrainer(trainer);
//        clientRepository.save(client);

        trainer.getExerciseList().add(exercise);
        trainerRepository.save(trainer);

    }

    @AfterEach
    void tearDown() {
        trainerRepository.deleteById(trainer.getId());
//        clientRepository.deleteById(client.getClientId());
        exerciseRepository.deleteById(exercise.getId());
    }

    @Test
    public void saveTrainer_repositoryTest(){
        Optional<Trainer> trainerOptional = trainerRepository.findById(trainer.getId());
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