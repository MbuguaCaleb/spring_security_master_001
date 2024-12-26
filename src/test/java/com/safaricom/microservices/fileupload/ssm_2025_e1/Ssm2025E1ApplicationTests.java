package com.safaricom.microservices.fileupload.ssm_2025_e1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Ssm2025E1ApplicationTests {

    //Mock Mvc iS Used for Integration Tests
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("""
            When calling the /hello endpoint authenicated we should get back hello in the
            response body and a http status of 200 OK
            """)
    @WithMockUser //creates a mock security context with a user inside
    void helloAuthenticated() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello"));
    }

    @Test
    @DisplayName("""
            When calling the /hello endpoint authenicated we should get back hello in the
            response body and a http status of 401 Unauthorized
            """)
    void helloUnAuthenticated() throws Exception {
        mockMvc.perform(get("/hello")).andExpect(status().isUnauthorized());

    }

}
