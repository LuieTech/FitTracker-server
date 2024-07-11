package luitech.java.trainerTracker_server.repository;

import luitech.java.trainerTracker_server.model.Exercise;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ExerciseRepositoryTest {

    @Autowired
    ExerciseRepository exerciseRepository;

    Exercise exercise;

    @BeforeEach
    void setUp() {

        exercise = new Exercise("pull down", "slow tempo", "back");
        exerciseRepository.save(exercise);
        System.out.println("This is the new exercise: "+ exercise);

    }

    @AfterEach
    void tearDown() {

        exerciseRepository.deleteById(exercise.getId());

    }

    @Test
    public void saveExercise_repositoryTest(){
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(exercise.getId());
        assertTrue(exerciseOptional.isPresent());
        System.out.println(exerciseOptional.get().getId());
    }

}