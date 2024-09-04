package fr.dawan.santeplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.santeplus.dto.AuthRequest;
import fr.dawan.santeplus.util.JwtUtil;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        return jwtUtil.generateToken(userDetails.getUsername());
    }
    
    
    @GetMapping("/user")
    public UserDetails getUserDetails(@RequestHeader("Authorization") String token) throws Exception {
        String username = jwtUtil.extractUsername(token.substring(7)); // remove "Bearer " from the token
        return userDetailsService.loadUserByUsername(username);
    }

    @PutMapping("/updatePassword")
    public String updatePassword(@RequestHeader("Authorization") String token, @RequestBody AuthRequest authRequest) throws Exception {
        String username = jwtUtil.extractUsername(token.substring(7)); // remove "Bearer " from the token
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        
        // Assuming you have a method to update the password
        // Update password in the database or user details
        String encodedPassword = passwordEncoder.encode(authRequest.getPassword());
        // Call a service to update password in the database
        // userService.updatePassword(username, encodedPassword);
        return "Password updated successfully";
    }
}

