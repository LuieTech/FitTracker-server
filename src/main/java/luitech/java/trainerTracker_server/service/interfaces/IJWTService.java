package luitech.java.trainerTracker_server.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJWTService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    public boolean isTokenValid(String token, UserDetails userDetails);

}
