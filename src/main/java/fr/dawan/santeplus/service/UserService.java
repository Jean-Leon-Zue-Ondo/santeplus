package fr.dawan.santeplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.dawan.santeplus.entities.User;
import fr.dawan.santeplus.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
    	
        // Encodage du mot de passe avant de sauvegarder l'utilisateur
    	
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // Vous pouvez également ajouter d'autres méthodes pour la gestion des utilisateurs
}