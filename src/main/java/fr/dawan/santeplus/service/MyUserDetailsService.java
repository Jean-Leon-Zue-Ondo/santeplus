package fr.dawan.santeplus.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.dawan.santeplus.entities.User;
import fr.dawan.santeplus.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	    @Autowired
	    private UserRepository userRepository;  // Injectez votre repository

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    	
	        // Rechercher l'utilisateur dans la base de données par nom d'utilisateur
	    	
	        User user = userRepository.findByUsername(username);
	        
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found with username: " + username);
	        }
	        
	        // Convertir votre entité User en un objet UserDetails
	        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
	    }
}

