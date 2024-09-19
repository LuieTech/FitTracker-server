//package luitech.java.trainerTracker_server.controller.impl;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import luitech.java.trainerTracker_server.model.Exercise;
//import luitech.java.trainerTracker_server.repository.ExerciseRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//class ExerciseControllerTest {
//
//    @Autowired
//    ExerciseRepository exerciseRepository;
//
//    @Autowired
//    WebApplicationContext webApplicationContext;
//    MockMvc mockMvc;
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    Exercise exercise1;
//    Exercise exercise2;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        exercise1 = new Exercise();
//        exercise1.setName("pull down");
//        exercise1.setInstructions("slow tempo 1-2-3");
//        exercise1.setBodyPart("back");
//        exerciseRepository.save(exercise1);
//
//        exercise2 = new Exercise();
//        exercise2.setName("push ups");
//        exercise2.setInstructions("slow tempo 1-2-3");
//        exercise2.setBodyPart("chest");
//        exerciseRepository.save(exercise2);
//    }
//
//    @AfterEach
//    void tearDown() {
//        exerciseRepository.deleteById(exercise1.getId());
//        exerciseRepository.deleteById(exercise2.getId());
//    }
//
//    @Test
//    void getAllExercises_test() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(get("/api/exercises"))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//        assertTrue(mvcResult.getResponse().getContentAsString().contains("push ups"));
//        System.out.println("This is the exercise1: "+ exercise1.getId());
//    }
//
//    @Test
//    void getExerciseById_test() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(get("/api/exercises/"+exercise1.getId()).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//
//    @Test
//    void saveExercise() throws Exception {
//        String body = objectMapper.writeValueAsString(exercise2);
//        MvcResult mvcResult = mockMvc.perform(post("/api/exercises").content(body).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        assertTrue(exerciseRepository.findAll().toString().contains("pull down"));
//    }
//    @Test
//    void deleteExercise_test() throws Exception {
//        exerciseRepository.save(exercise2);
//
//        mockMvc.perform(delete("/api/exercises/"+exercise2.getId()))
//                .andExpect(status().isNoContent())
//                .andReturn();
//
//        assertFalse(exerciseRepository.findAll().toString().contains("push ups"));
//    }
//}