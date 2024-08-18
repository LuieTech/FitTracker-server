package luitech.java.trainerTracker_server.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface IJWTService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    public boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
}
