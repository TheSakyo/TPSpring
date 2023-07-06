package fr.thesakyo.TPSpring.service;

import fr.thesakyo.TPSpring.model.User;
import fr.thesakyo.TPSpring.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Data
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(final Long id) { return userRepository.findById(id); }
    public Iterable<User> getUsers() { return userRepository.findAll(); }

    public void deleteUser(final Long id) { userRepository.deleteById(id); }
    public User saveUser(User user) {

        User savedUser = userRepository.save(user);
        return savedUser;
    }

}