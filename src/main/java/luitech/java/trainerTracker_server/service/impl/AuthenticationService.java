package luitech.java.trainerTracker_server.service.impl;

import lombok.RequiredArgsConstructor;
import luitech.java.trainerTracker_server.controller.dto.RegisterRequestDTO;
import luitech.java.trainerTracker_server.model.Trainer;
import luitech.java.trainerTracker_server.repository.TrainerRepository;
import luitech.java.trainerTracker_server.service.interfaces.IAuthenticationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final TrainerRepository trainerRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Trainer register(RegisterRequestDTO registerRequestDTO) {
        Trainer trainer = new Trainer();
        trainer.setEmail(registerRequestDTO.getEmail());
        trainer.setName(registerRequestDTO.getName());
        trainer.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));

        return trainerRepository.save(trainer);
    }

}
