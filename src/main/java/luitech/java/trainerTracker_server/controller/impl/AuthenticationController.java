package luitech.java.trainerTracker_server.controller.impl;

import lombok.RequiredArgsConstructor;
import luitech.java.trainerTracker_server.controller.dto.JwtAuthenticationResponse;
import luitech.java.trainerTracker_server.controller.dto.LoginRequestDTO;
import luitech.java.trainerTracker_server.controller.dto.RefreshTokenReqDTO;
import luitech.java.trainerTracker_server.controller.dto.RegisterRequestDTO;
import luitech.java.trainerTracker_server.model.Trainer;
import luitech.java.trainerTracker_server.service.interfaces.IAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Trainer> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        return ResponseEntity.ok(authenticationService.register(registerRequestDTO));
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


}
