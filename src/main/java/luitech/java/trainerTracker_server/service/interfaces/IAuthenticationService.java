package luitech.java.trainerTracker_server.service.interfaces;

import luitech.java.trainerTracker_server.controller.dto.JwtAuthenticationResponse;
import luitech.java.trainerTracker_server.controller.dto.LoginRequestDTO;
import luitech.java.trainerTracker_server.controller.dto.RefreshTokenReqDTO;
import luitech.java.trainerTracker_server.controller.dto.RegisterRequestDTO;
import luitech.java.trainerTracker_server.model.Trainer;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthenticationService {

    Trainer register(RegisterRequestDTO registerRequestDTO);

    JwtAuthenticationResponse login(@RequestBody LoginRequestDTO loginRequestDTO);

    JwtAuthenticationResponse refreshToken(RefreshTokenReqDTO refreshTokenReqDTO);
}
