package luitech.java.trainerTracker_server.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import luitech.java.trainerTracker_server.model.Trainer;
import luitech.java.trainerTracker_server.service.interfaces.IJWTService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements IJWTService {

    @Value("${jwt.secret}")
    private String jwtSecret;


    public String generateToken(UserDetails userDetails) {

        String subject = ((Trainer) userDetails).getEmail();  // Siempre usa el email como subject
        return Jwts.builder().setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    @Override
    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        String subject = userDetails.getUsername() != null ? userDetails.getUsername() : ((Trainer) userDetails).getEmail();

        return Jwts.builder().setClaims(extraClaims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 604800000)) // 7 days
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers){
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

private Key getSignKey() {
    if (jwtSecret == null || jwtSecret.isEmpty()) {
        throw new IllegalArgumentException("JWT_SECRET environment variable is not set.");
    }
    byte[] key = Decoders.BASE64.decode(jwtSecret);
    return Keys.hmacShaKeyFor(key);
}

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token){
        Date expirationDate = extractClaim(token, Claims::getExpiration);
        Date currentDate = new Date();
        return expirationDate.before(currentDate);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String email = extractEmail(token);

        if (email == null) {
            return false;
        }

        return (email.equals(((Trainer) userDetails).getEmail()) && !isTokenExpired(token));
    }

}
