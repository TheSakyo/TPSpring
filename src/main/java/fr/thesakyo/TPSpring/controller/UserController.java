package fr.thesakyo.TPSpring.controller;


import fr.thesakyo.TPSpring.model.User;
import fr.thesakyo.TPSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Iterable<User> getUsers() { return userService.getUsers(); }
    @GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") final Long id) {

		Optional<User> user = userService.getUser(id);
        return user.orElse(null);
	}

    @PostMapping("/user")
    public User createUser(@RequestBody User User) { return userService.saveUser(User); }
    @PutMapping("/user/{id}")
	public User updateUser(@PathVariable("id") final Long id, @RequestBody User user) {

		Optional<User> targetUser = userService.getUser(id);

		if(targetUser.isPresent()) {

			User currentUser = targetUser.get();

			String firstname = user.getFirstName();
			if(firstname != null) {	currentUser.setFirstName(firstname); }

			String lastName = user.getLastName();
			if(lastName != null) { currentUser.setLastName(lastName); }

			String mail = user.getMail();
			if(mail != null) { currentUser.setMail(mail); }

            String password = user.getPassword();
			if(password != null) { currentUser.setPassword(password); }

			userService.saveUser(currentUser);
			return currentUser;

		} else { return null; }
	}
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable("id") final Long id) { userService.deleteUser(id); }
}
