package fr.dawan.santeplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.santeplus.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // Méthode pour trouver un utilisateur par nom d'utilisateur
    User findByUsername(String username);
}
