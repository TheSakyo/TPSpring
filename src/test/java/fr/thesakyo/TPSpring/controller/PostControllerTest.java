package fr.thesakyo.TPSpring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPostsTest() throws Exception {

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPostTest() throws Exception {

        mockMvc.perform(get("/post/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Les chips")));
    }

    @Test
    public void createPostTest() throws Exception {

        String title = "\"title\": \"La weed\"";
        String content = "\"content\": \"Stupéfiant aux propriétés hallucinogènes et euphorisantes, extrait du chanvre indien\"";
        String imageUrl = "\"imageUrl\": \"https://cbd-dundees.com/wp-content/uploads/2020/07/lexique-cannabis.jpg\"";
        String user = "\"user\": { \"id\": 2 }";

        String requestBody = String.format("{%title, %content, %imageUrl, %user}",
                title, content, imageUrl, user);

        mockMvc.perform(post("/user").content(requestBody).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title));
    }

    @Test
    public void updateUserTest() throws Exception {

        String title = "\"title\": \"Le canabis\"";
        String content = "\"content\": \"Stupéfiant aux propriétés hallucinogènes et euphorisantes, extrait du chanvre indien\"";
        String imageUrl = "\"imageUrl\": \"https://cbd-dundees.com/wp-content/uploads/2020/07/lexique-cannabis.jpg\"";
        String user = "\"user\": { \"id\": 2 }";

        String requestBody = String.format("{%title, %content, %imageUrl, %user}",
                title, content, imageUrl, user);

        mockMvc.perform(put("/user/{id}", 1).content(requestBody).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("La weed"))
                .andExpect(jsonPath("$.content").value(content));
    }

   /* @DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable("id") final Long id) { userService.deleteUser(id); }*/
}