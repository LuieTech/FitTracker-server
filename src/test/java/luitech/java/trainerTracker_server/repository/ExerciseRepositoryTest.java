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

    }

    @AfterEach
    void tearDown() {

        exerciseRepository.deleteById(exercise.getExerciseId());

    }

    @Test
    public void saveExercise_repositoryTest(){
        exerciseRepository.findById(exercise.getExerciseId());
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(exercise.getExerciseId());
        assertTrue(exerciseOptional.isPresent());
        assertEquals("pull down", exercise.getName());
    }

}