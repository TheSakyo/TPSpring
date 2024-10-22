package fr.thesakyo.TPSpring.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUsersTest() throws Exception { mockMvc.perform(get("/users")).andExpect(status().isOk()); }

    @Test
    public void getUserTest() throws Exception {

        mockMvc.perform(get("/user/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Tommy")));
    }

    @Test
    public void createUserTest() throws Exception {

        String firstname = "\"firstName\": \"Rémy\"";
        String lastname = "\"lastName\": \"Moro\"";
        String mail = "\"mail\": \"remy.moro@gmail.com\"";
        String password = "\"password\": \"remy123\"";

        String requestBody = "{ " + firstname + ", " + lastname + ", " + mail + ", " + password + " }";

        mockMvc.perform(post("/user").content(requestBody).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Rémy"));
    }

    @Test
    public void updateUserTest() throws Exception {

        String firstname = "\"firstName\": \"Enzo\"";
        String lastname = "\"lastName\": \"Cardone\"";
        String mail = "\"mail\": \"sacha.cardone@gmail.com\"";
        String password = "\"password\": \"sacha123\"";

        String requestBody = "{ " + firstname + ", " + lastname + ", " + mail + ", " + password + " }";

        mockMvc.perform(put("/user/{id}", 1).content(requestBody).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Enzo"))
                .andExpect(jsonPath("$.lastName").value("Cardone"));
    }

   /*@Test
   public void deleteUserTest() throws Exception {

        mockMvc.perform(delete("/user/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Enzo")));
   }*/
}
