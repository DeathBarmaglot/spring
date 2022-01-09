package com.shop.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.store.entity.Role;
import com.shop.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TestInsertObject {
    @Autowired
    private MockMvc mvc;

    @Test
    public void createUserAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/registration")
                        .content(asJsonString(new User("firstName4", "lastName4", "pwd", "email4@mail.com", Role.USER,true,true)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}