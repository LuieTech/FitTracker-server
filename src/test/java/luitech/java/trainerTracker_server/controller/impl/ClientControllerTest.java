package luitech.java.trainerTracker_server.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import luitech.java.trainerTracker_server.controller.dto.EmailDTO;
import luitech.java.trainerTracker_server.controller.dto.PasswordDTO;
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
class ClientControllerTest {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TrainerRepository trainerRepository;
    @Autowired
    ExerciseRepository exerciseRepository;
    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    Client clientToDelete;
    Client client1;
    Trainer trainer1;
    Exercise exercise1;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        clientToDelete = new Client();

        clientToDelete.setComment("This is a client to test delete method");
        clientRepository.save(clientToDelete);

        client1 = new Client();

        client1.setComment("full body 3x per week");
        clientRepository.save(client1);

        trainerRepository.save(trainer1);

        exercise1 = new Exercise();
        exercise1.setName("lunges");
        exercise1.setBodyPart("legs");
        exerciseRepository.save(exercise1);

        client1.setTrainer(trainer1);
        clientRepository.save(client1);

    }

    @AfterEach
    void tearDown() {
        clientRepository.deleteById(clientToDelete.getId());
        clientRepository.deleteById(client1.getId());
        exerciseRepository.deleteById(exercise1.getId());
    }
    @Test
    void getAllClients_Test() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    void getClientById_invalidId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/clients/34567").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void saveClient_test() throws Exception {
        String body = objectMapper.writeValueAsString(client1);
        MvcResult mvcResult = mockMvc.perform(post("/api/clients").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(clientRepository.findAll().toString().contains("henryTech"));
    }

    @Test
    void updateClientPassword_test() throws Exception {
        PasswordDTO passwordDTO = new PasswordDTO("567890");
        String body = objectMapper.writeValueAsString(passwordDTO);
        mockMvc.perform(patch("/api/clients/password/"+client1.getId()).content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
        assertTrue(clientRepository.findById(client1.getId()).toString().contains("567890"));
    }
    @Test
    void updateClientEmail_test() throws Exception {
        EmailDTO emailDTO = new EmailDTO("kevin@example.com");
        String body = objectMapper.writeValueAsString(emailDTO);
        mockMvc.perform(patch("/api/clients/email/"+client1.getId()).content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
        assertTrue(clientRepository.findById(client1.getId()).toString().contains("kevin@example.com"));
    }
    @Test
    void deleteTrainer_test() throws Exception {
        clientRepository.save(clientToDelete);

        mockMvc.perform(delete("/api/clients/"+clientToDelete.getId()))
                .andExpect(status().isNoContent())
                .andReturn();

        assertFalse(trainerRepository.findAll().toString().contains("Raul"));
    }


}