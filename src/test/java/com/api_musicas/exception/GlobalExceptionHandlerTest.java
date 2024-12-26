package com.api_musicas.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(globalExceptionHandler).build();
    }

    @Test
    public void testHandleValidationExceptions_MethodArgumentNotValidException() {
        BindingResult bindingResult = mock(BindingResult.class);
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);

        FieldError fieldError = new FieldError("teste", "teste", "teste erro");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleValidationExceptions(exception);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("teste erro", response.getBody().get("teste"));
    }

    @Test
    public void testHandleValidationExceptions_RuntimeException() {
        RuntimeException exception = new RuntimeException("teste erro");

        ResponseEntity<String> response = globalExceptionHandler.handleValidationExceptions(exception);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("teste erro", response.getBody());
    }
}