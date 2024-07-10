package luitech.java.trainerTracker_server.service.interfaces;

import luitech.java.trainerTracker_server.model.Exercise;

import java.util.List;

public interface IExerciseService {
    
    List<Exercise> getAllExercises();

    Exercise getExerciseById(Integer id);

    void saveExercise(Exercise exerciseBody);
}
