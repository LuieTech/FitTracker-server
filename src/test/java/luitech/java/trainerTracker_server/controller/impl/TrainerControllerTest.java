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

    }

    @AfterEach
    void tearDown() {
        trainerRepository.deleteById(trainer1.getId());
    }

    @Test
    void getAllTrainers_test() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/trainers"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Jacob Williams"));

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

//        assertTrue(trainerRepository.findAll().toString().contains("Jacob Williams"));
    }

    @Test
    void updateTrainer_test() throws Exception{
        Trainer updatedInfo = new Trainer("Marco Ruiz", "Marco", "marco@example.com", 12345678);
        updatedInfo.setId(trainer1.getId());
        String body = objectMapper.writeValueAsString(updatedInfo);
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
}