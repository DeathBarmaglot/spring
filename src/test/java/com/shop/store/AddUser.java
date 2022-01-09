package com.shop.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.store.entity.User;
import com.shop.store.service.UserService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserRestControllerTest {

    @MockBean
    private UserService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /Users success")
    void testGetUsersSuccess() throws Exception {
        User User1 = new User(1L, "User Name", "Description");
        User User2 = new User(2L, "User 2 Name", "Description 2");
        doReturn(Lists.newArrayList(User1, User2)).when(service).findAll();
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.LOCATION, "/registration"))
                    .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("User Name")))
                .andExpect(jsonPath("$[0].description", is("Description")))
                .andExpect(jsonPath("$[0].version", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("User 2 Name")))
                .andExpect(jsonPath("$[1].description", is("Description 2")))
                .andExpect(jsonPath("$[1].version", is(4)));
    }

    @Test
    @DisplayName("GET /registration/1")
    void testGetUserById() throws Exception {
        User User = new User(1L, "User Name", "Description");
        doReturn(Optional.of(User)).when(service).findById(1L);

        mockMvc.perform(get("/registration/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(header().string(HttpHeaders.LOCATION, "/registration/1"))
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("User Name")))
                .andExpect(jsonPath("$.description", is("Description")))
                .andExpect(jsonPath("$.version", is(1)));
    }

    @Test
    @DisplayName("GET /registration/1 - Not Found")
    void testGetUserByIdNotFound() throws Exception {
        doReturn(Optional.empty()).when(service).findById(1L);

        mockMvc.perform(get("/registration/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /registration")
    void testCreateUser() throws Exception {
        User UserToPost = new User("New User", "This is my User");
        User UserToReturn = new User(1L, "New User", "This is my User");
        doReturn(UserToReturn).when(service).save(any());

        mockMvc.perform(post("/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(UserToPost)))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(header().string(HttpHeaders.LOCATION, "/registration/1"))
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("New User")))
                .andExpect(jsonPath("$.description", is("This is my User")))
                .andExpect(jsonPath("$.version", is(1)));
    }

    @Test
    @DisplayName("PUT /registration/1")
    void testUpdateUser() throws Exception {
        User UserToPut = new User("New User", "This is my User");
        User UserToReturnFindBy = new User(1L, "New User", "This is my User");
        User UserToReturnSave = new User(1L, "New User", "This is my User");
        doReturn(Optional.of(UserToReturnFindBy)).when(service).findById(1L);
        doReturn(UserToReturnSave).when(service).save(any());

        mockMvc.perform(post("/registration/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.IF_MATCH, 2)
                        .content(asJsonString(UserToPut)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(header().string(HttpHeaders.LOCATION, "/registration/1"))
                .andExpect(header().string(HttpHeaders.ETAG, "\"3\""))

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("New User")))
                .andExpect(jsonPath("$.description", is("This is my User")))
                .andExpect(jsonPath("$.version", is(3)));
    }

    @Test
    @DisplayName("PUT /registration/1 - Conflict")
    void testUpdateUserConflict() throws Exception {
        User UserToPut = new User("New User", "This is my User");
        User UserToReturn = new User(1L, "New User", "This is my User");
        doReturn(Optional.of(UserToReturn)).when(service).findById(1L);
        doReturn(UserToReturn).when(service).save(any());

        mockMvc.perform(post("/registration/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.IF_MATCH, 3)
                        .content(asJsonString(UserToPut)))

                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("PUT /registration/1 - Not Found")
    void testUpdateUserNotFound() throws Exception {

        User UserToPut = new User("New User", "This is my User");
        doReturn(Optional.empty()).when(service).findById(1L);

        mockMvc.perform(post("/registration/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.IF_MATCH, 3)
                        .content(asJsonString(UserToPut)))
                .andExpect(status().isNotFound());
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}