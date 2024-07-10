package luitech.java.trainerTracker_server.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import luitech.java.trainerTracker_server.model.Client;
import luitech.java.trainerTracker_server.model.Exercise;
import luitech.java.trainerTracker_server.model.Trainer;
import luitech.java.trainerTracker_server.repository.ClientRepository;
import luitech.java.trainerTracker_server.repository.ExerciseRepository;
import luitech.java.trainerTracker_server.repository.TrainerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TrainerControllerTest {

    @Autowired
    TrainerRepository trainerRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ExerciseRepository exerciseRepository;
    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    Trainer trainer1;
    Trainer trainer2;

    Client client1;
    Exercise exercise1;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        trainer1 = new Trainer("Jacob Williams", "jacob", "jw@example.com", 12345);
        trainerRepository.save(trainer1);
        client1 = new Client();
        client1.setName("alexander rubio");
        client1.setEmail("alex@example.com");
        client1.setPassword("1234");
        client1.setUsername("alex");
        client1.setComment("Cardio 3x weekly");
        client1.setTrainerId(trainer1.getTrainerId());
        clientRepository.save(client1);
        trainer1.addClient(client1);
        trainerRepository.save(trainer1);

        trainer2 = new Trainer("Val Rodriguez", "val", "val@example.com", 56789);
        trainerRepository.save(trainer2);
        exercise1 = new Exercise();
        exercise1.setName("pull down");
        exercise1.setDescription("slow tempo");
        exercise1.setBodyPart("back");
        exerciseRepository.save(exercise1);
        trainer2.addExercise(exercise1);
        trainerRepository.save(trainer2);

    }

    @AfterEach
    void tearDown() {

        trainerRepository.deleteById(trainer1.getTrainerId());
        trainerRepository.deleteById(trainer2.getTrainerId());
        clientRepository.deleteById(client1.getClientId());
        exerciseRepository.deleteById(exercise1.getExerciseId());
    }

    @Test
    void getAllTrainers_test() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/trainers"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Val Rodriguez"));
        System.out.println("This is the trainer1: "+ trainer1.getTrainerId());
    }

    @Test
    void getTrainerById_invalidId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/trainers/34567").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void saveTrainer() throws Exception {
        String body = objectMapper.writeValueAsString(trainer1);
        MvcResult mvcResult = mockMvc.perform(post("/api/trainers").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(trainerRepository.findAll().toString().contains("Val Rodriguez"));
    }

    @Test
    void updateTrainer_test() throws Exception{
        Trainer updatedInfo = new Trainer("Marco Ruiz", "Marco", "marco@example.com", 12345678);
        updatedInfo.setTrainerId(trainer1.getTrainerId());
        String body = objectMapper.writeValueAsString(updatedInfo);
        MvcResult mvcResult = mockMvc.perform(put("/api/trainers/"+trainer1.getTrainerId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        Trainer foundTrainer = trainerRepository.findById(trainer1.getTrainerId()).orElseThrow();
        assertEquals("Marco Ruiz", foundTrainer.getName());
        assertEquals("Marco", foundTrainer.getUsername());
        assertEquals("marco@example.com", foundTrainer.getEmail());
        assertEquals(12345678, foundTrainer.getPhoneNumber());

    }
}