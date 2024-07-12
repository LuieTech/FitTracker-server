package luitech.java.trainerTracker_server.controller.impl;

import luitech.java.trainerTracker_server.model.Exercise;
import luitech.java.trainerTracker_server.service.interfaces.IExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExerciseController {
    @Autowired
    IExerciseService exerciseService;

    @GetMapping("/exercises")
    public List<Exercise> getAllExercises(){
        return exerciseService.getAllExercises();
    }

    @GetMapping("/exercises/{id}")
    public Exercise getExerciseById(@PathVariable Integer id){
        return exerciseService.getExerciseById(id);
    }

    @PostMapping("/exercises")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExercise(@RequestBody Exercise exerciseBody){
        exerciseService.saveExercise(exerciseBody);
    }

    @DeleteMapping("/exercises/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Integer id){
        exerciseService.deleteExercise(id);
    }

}
