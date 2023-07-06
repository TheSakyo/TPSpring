package fr.thesakyo.TPSpring.controller;

import fr.thesakyo.TPSpring.model.User;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getUsersTest() throws Exception {

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("John")));
    }

    @Test
    public void getUserTest(@PathVariable("id") final Long id) throws Exception {

        mockMvc.perform(get("/user/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("John")));
    }
   /* @GetMapping("/user/{id}")
	public User getPost(@PathVariable("id") final Long id) {

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
	public void deleteUser(@PathVariable("id") final Long id) { userService.deleteUser(id); }*/
}
