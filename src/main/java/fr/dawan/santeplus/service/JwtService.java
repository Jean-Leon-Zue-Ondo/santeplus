package fr.dawan.santeplus.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import fr.dawan.santeplus.config.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

    @Autowired
    private JwtConfig jwtConfig;
    
    
    public String generateToken(String username) {
        String secret = jwtConfig.getSecret();
        long expiration = jwtConfig.getExpiration();

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


	public String extractUsername(String jwtToken) {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean validateToken(String jwtToken, UserDetails userDetails) {
		// TODO Auto-generated method stub
		return false;
	}
}