package luitech.java.trainerTracker_server.controller.impl;

import lombok.RequiredArgsConstructor;
import luitech.java.trainerTracker_server.controller.dto.*;
import luitech.java.trainerTracker_server.model.Trainer;
import luitech.java.trainerTracker_server.repository.TrainerRepository;
import luitech.java.trainerTracker_server.service.interfaces.IAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationService authenticationService;

    private final TrainerRepository trainerRepository;

    @PostMapping("/register")
    public ResponseEntity<TrainerResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        Trainer trainer = authenticationService.register(registerRequestDTO);
        TrainerResponseDTO trainerResponseDTO = new TrainerResponseDTO(trainer);
        return ResponseEntity.ok(trainerResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authenticationService.login(loginRequestDTO));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenReqDTO refreshTokenReqDTO){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenReqDTO));
    }

    @GetMapping("/me")
    public ResponseEntity<TrainerResponseDTO> getAuthenticatedTrainer(Principal principal) {
        // El principal contiene la información del usuario autenticado (el subject del token)
        String email = principal.getName();
        Trainer trainer = trainerRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer not found"));
        TrainerResponseDTO trainerResponseDTO = new TrainerResponseDTO(trainer); // Convierte tu entidad Trainer a un DTO para devolverlo
        return ResponseEntity.ok(trainerResponseDTO);
    }
}
