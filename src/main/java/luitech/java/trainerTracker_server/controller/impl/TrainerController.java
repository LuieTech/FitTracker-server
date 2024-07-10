package luitech.java.trainerTracker_server.controller.impl;

import luitech.java.trainerTracker_server.model.Trainer;
import luitech.java.trainerTracker_server.service.interfaces.ITrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrainerController {
    @Autowired
    ITrainerService trainerService;

    @GetMapping("/trainers")
    public List<Trainer> getAllTrainers(){
        return trainerService.getAllTrainers();
    }

    @GetMapping("/trainers/{id}")
    public Trainer getTrainerById(@PathVariable Integer id){
        return trainerService.getTrainerById(id);
    }

    @PostMapping("/trainers")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTrainer(@RequestBody Trainer trainerBody){
        trainerService.saveTrainer(trainerBody);
    }


}
