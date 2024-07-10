package luitech.java.trainerTracker_server.controller.impl;

import luitech.java.trainerTracker_server.service.interfaces.ITrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TrainerController {
    @Autowired
    ITrainerService trainerService;



}
