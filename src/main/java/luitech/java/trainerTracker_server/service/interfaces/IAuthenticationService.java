package luitech.java.trainerTracker_server.service.interfaces;

import luitech.java.trainerTracker_server.controller.dto.RegisterRequestDTO;
import luitech.java.trainerTracker_server.model.Trainer;

public interface IAuthenticationService {

    Trainer register(RegisterRequestDTO registerRequestDTO);

}
