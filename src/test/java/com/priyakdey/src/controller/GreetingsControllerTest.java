package com.priyakdey.src.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("UNIT TEST - GreetingsControllerTest")
@Tag("unit")
@WebMvcTest(controllers = GreetingsController.class)
class GreetingsControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    GreetingsControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @DisplayName("Should return 200 OK")
    @Test
    void shouldReturnGreetings() throws Exception {
        final var mockHttpServletRequestBuilder =
                MockMvcRequestBuilders
                        .get("/greetings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString("Howdy stranger, how are you doing today?"));

        final var response =
                mockMvc
                        .perform(mockHttpServletRequestBuilder)
                        .andReturn()
                        .getResponse();

        final var returnedResponseContent = response.getContentAsString();
        final var returnedResponseCode = response.getStatus();

        assertEquals(200, returnedResponseCode);
        assertEquals("Howdy stranger, how are you doing today?", returnedResponseContent);
    }

}