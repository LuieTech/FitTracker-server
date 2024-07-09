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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientRepositoryTest {

    @Autowired
            ClientRepository clientRepository;
    @Autowired
            ExerciseRepository exerciseRepository;

    Client client;
    Exercise exercise;
    List<Exercise> exerciseList = new ArrayList<>();

//    private String username;
//    private String password;
//    private String email;
//    private String comment;
//    @OneToMany
//    private List<Exercise> exercises = new ArrayList<>();
//    private Integer trainerId;

    @BeforeEach
    void setUp() {
        exercise = new Exercise("pull down", "slow tempo", "back");
        exerciseRepository.save(exercise);
        exerciseList.add(exercise);

        client = new Client("alex", "1234", "alex@example.com", "Cardio 3x weekly", exerciseList, 123);
        clientRepository.save(client);

    }

    @AfterEach
    void tearDown() {
        clientRepository.deleteById(client.getClientId());
        exerciseRepository.deleteById(exercise.getExerciseId());

    }

    @Test
    public void saveClient_repositoryTest(){
        clientRepository.findById(client.getClientId());
        Optional<Client> clientOptional = clientRepository.findById(client.getClientId());
        assertEquals(clientOptional.get().getUsername(), client.getUsername());
        System.out.println("This is client created: "+clientOptional.get());
    }

    @Test
    public void saveClient_invalidId_test(){
        Optional<Client> clientOptional = clientRepository.findById(999);
        assertTrue(clientOptional.isEmpty());
    }
}