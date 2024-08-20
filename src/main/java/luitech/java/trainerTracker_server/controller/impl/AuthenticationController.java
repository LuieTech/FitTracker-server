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
        System.out.println("This is the method refresh: "+ refreshTokenReqDTO);
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenReqDTO));
    }

    @GetMapping("/me")
    public ResponseEntity<TrainerResponseDTO> getAuthenticatedTrainer(Principal principal) {
        // El principal contiene la información del usuario autenticado (el subject del token)
        System.out.println("This is the principal: "+principal);
        String email = principal.getName();
        System.out.println("this is the email we got from principal: "+email);
        Trainer trainer = trainerRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer not found"));
        System.out.println("this is the trainer found: "+ trainer);
        TrainerResponseDTO trainerResponseDTO = new TrainerResponseDTO(trainer); // Convierte tu entidad Trainer a un DTO para devolverlo
        return ResponseEntity.ok(trainerResponseDTO);
    }
}
