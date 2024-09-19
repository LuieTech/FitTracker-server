//package luitech.java.trainerTracker_server.repository;
//
//import luitech.java.trainerTracker_server.model.Exercise;
//import luitech.java.trainerTracker_server.model.Trainer;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//class ExerciseRepositoryTest {
//
//    @Autowired
//    ExerciseRepository exerciseRepository;
//    @Autowired
//    TrainerRepository trainerRepository;
//    Trainer trainer;
//    Exercise exercise;
//
//    @BeforeEach
//    void setUp() {
//
//        trainer = new Trainer();
//        trainer.setName("jorge");
//        trainerRepository.save(trainer);
//        Optional<Trainer> trainerOptional = trainerRepository.findById(trainer.getId());
//        if(trainerOptional.isPresent()){
//            exercise = new Exercise("pull down", "slow tempo", "back");
//            exercise.setTrainer(trainerOptional.get());
//            exerciseRepository.save(exercise);
//        } else{ throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer not found");}
//
//    }
//
//    @AfterEach
//    void tearDown() {
//        exerciseRepository.deleteById(exercise.getId());
//        trainerRepository.deleteById(trainer.getId());
//    }
//
//    @Test
//    public void saveExercise_repositoryTest(){
//        Optional<Exercise> exerciseOptional = exerciseRepository.findById(exercise.getId());
//        assertTrue(exerciseOptional.isPresent());
//        System.out.println(exerciseOptional.get().getId());
//    }
//
//}