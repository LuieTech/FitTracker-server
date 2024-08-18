package luitech.java.trainerTracker_server.service.impl;

import lombok.RequiredArgsConstructor;
import luitech.java.trainerTracker_server.controller.dto.JwtAuthenticationResponse;
import luitech.java.trainerTracker_server.controller.dto.LoginRequestDTO;
import luitech.java.trainerTracker_server.controller.dto.RegisterRequestDTO;
import luitech.java.trainerTracker_server.model.Trainer;
import luitech.java.trainerTracker_server.repository.TrainerRepository;
import luitech.java.trainerTracker_server.service.interfaces.IAuthenticationService;
import luitech.java.trainerTracker_server.service.interfaces.IJWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final TrainerRepository trainerRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final IJWTService ijwtService;

    @Override
    public Trainer register(RegisterRequestDTO registerRequestDTO) {
        Trainer trainer = new Trainer();
        trainer.setEmail(registerRequestDTO.getEmail());
        trainer.setName(registerRequestDTO.getName());
        trainer.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));

        return trainerRepository.save(trainer);
    }

    public JwtAuthenticationResponse login(@RequestBody LoginRequestDTO loginRequestDTO){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(),
                loginRequestDTO.getPassword()));

        var trainer = trainerRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid EMAIL or PASSWORD"));
        var jwt = ijwtService.generateToken(trainer);
        var refreshToken = ijwtService.generateRefreshToken(new HashMap<>(), trainer);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;

    }

}
