package luitech.java.trainerTracker_server.service.impl;
import luitech.java.trainerTracker_server.model.Client;
import luitech.java.trainerTracker_server.model.Exercise;
import luitech.java.trainerTracker_server.repository.ExerciseRepository;
import luitech.java.trainerTracker_server.service.interfaces.IExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Service
public class ExerciseService implements IExerciseService {

    @Autowired
    ExerciseRepository exerciseRepository;
    @Override
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise getExerciseById(Integer id) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);
        if(exerciseOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise "+id+" not found");
        return exerciseOptional.get();
    }

    @Override
    public void saveExercise(Exercise exerciseBody) {
        exerciseRepository.save(exerciseBody);
    }

    @Override
    public void deleteExercise(Integer id) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);
        if (exerciseOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise"+id+"not found");
        exerciseRepository.deleteById(id);
    }

}
