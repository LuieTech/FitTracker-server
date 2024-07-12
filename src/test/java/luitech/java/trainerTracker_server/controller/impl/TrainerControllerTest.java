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

import java.util.Optional;

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
    Trainer trainerToDelete;
    Client client1;
    Exercise exercise1;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        trainerToDelete = new Trainer("Marc Jacobs", "Marc", "mj@example.com", 12345678);
        trainerRepository.save(trainerToDelete);

        trainer1 = new Trainer("Jacob Williams", "jacob", "jw@example.com", 12345);
        trainerRepository.save(trainer1);

        client1 = new Client();
        client1.setUsername("juan");
        client1.setPassword("1234");
        client1.setComment("Testing Trainer Controllers");
        clientRepository.save(client1);

        exercise1 = new Exercise();
        exercise1.setName("lunges");
        exercise1.setDescription("medium tempo");
        exercise1.setBodyPart("legs");
        exerciseRepository.save(exercise1);

        Optional<Trainer> trainerOptional = trainerRepository.findById(trainer1.getId());
        if(trainerOptional.isPresent()){
            exercise1.setTrainer(trainer1);
            exerciseRepository.save(exercise1);
            client1.setTrainer(trainer1);
            clientRepository.save(client1);
        }
    }

    @AfterEach
    void tearDown() {
        clientRepository.deleteById(client1.getId());
        exerciseRepository.deleteById(exercise1.getId());
        trainerRepository.deleteById(trainer1.getId());
        trainerRepository.deleteById(trainerToDelete.getId());
    }

    @Test
    void getAllTrainers_test() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/trainers"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Jacob Williams"));
        System.out.println(trainer1);

    }

    @Test
    void getTrainerById_test() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/trainers/"+trainer1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    void saveTrainer() throws Exception {
        String body = objectMapper.writeValueAsString(trainer1);
        MvcResult mvcResult = mockMvc.perform(post("/api/trainers").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void updateTrainer_test() throws Exception{
        Trainer newInfo = new Trainer("Marco Ruiz", "Marco", "marco@example.com", 12345678);
        newInfo.setId(trainer1.getId());
        String body = objectMapper.writeValueAsString(newInfo);
        MvcResult mvcResult = mockMvc.perform(put("/api/trainers/"+trainer1.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        Trainer foundTrainer = trainerRepository.findById(trainer1.getId()).orElseThrow();
        assertEquals("Marco Ruiz", foundTrainer.getName());
        assertEquals("Marco", foundTrainer.getUsername());
        assertEquals("marco@example.com", foundTrainer.getEmail());
        assertEquals(12345678, foundTrainer.getPhoneNumber());
    }

    @Test
    void deleteTrainer() throws Exception {
        trainerRepository.save(trainerToDelete);

        mockMvc.perform(delete("/api/trainers/"+trainerToDelete.getId()))
                .andExpect(status().isNoContent())
                .andReturn();

        assertFalse(trainerRepository.findAll().toString().contains("Marc Jacobs"));
    }
}