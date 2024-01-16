package com.example.Exampledemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExampleRestController.class)
public class ExampleRestControllerTest {

    @Mock
    private ExampleService exampleService;

    @InjectMocks
    private ExampleRestController exampleRestController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllExample() throws Exception {
        // Arrange
        List<Example> exampleList = new ArrayList<>();
        exampleList.add(new Example(1, "Lenevo", "LU58601", "Desktop", "2025"));
        exampleList.add(new Example(2, "Lenovo", "LA89510", "Desktop", "2026"));

        when(exampleService.getAllExamples()).thenReturn(exampleList);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/examples"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(exampleList.size()));
    }

    @Test
    void getExampleById() throws Exception {
        // Arrange
        int exampleId = 1;
        Example example = new Example(exampleId, "Lenevo", "LU58601", "Desktop", "2025");

        when(exampleService.getExampleById(exampleId)).thenReturn(example);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/examples/{id}", exampleId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(example.getId()))
                .andExpect(jsonPath("$.name").value(example.getName()));
    }

    @Test
    void addExample() throws Exception {
        // Arrange
        Example example = new Example(1, "Lenovo", "LU58601", "Desktop", "2025");

        when(exampleService.createExample(any(Example.class))).thenReturn(example);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/examples/addexample")
                        .content(new ObjectMapper().writeValueAsString(example))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(example.getId()))
                .andExpect(jsonPath("$.name").value(example.getName()));
    }

    @Test
    void updateExample() throws Exception {
        // Arrange
        Example example = new Example(1, "Lenovo", "LU58601", "Desktop", "2027");

        when(exampleService.updateExample(any(Example.class))).thenReturn(example);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/examples/updateexample")
                        .content(new ObjectMapper().writeValueAsString(example))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(example.getId()))
                .andExpect(jsonPath("$.name").value(example.getName()));
    }

    @Test
    void deleteExampleById() throws Exception {
        // Arrange
        int exampleId = 1;

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/examples/delete/{id}", exampleId))
                .andExpect(status().isOk());

        verify(exampleService, times(1)).deleteById(exampleId);
    }

    @Test
    void deleteExample() throws Exception {
        // Arrange
        Example example = new Example(1, "Lenovo", "LA89510", "Desktop", "2026");

        when(exampleService.deleteExample(any(Example.class))).thenReturn(example);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/examples/delete")
                        .content(new ObjectMapper().writeValueAsString(example))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(example.getId()))
                .andExpect(jsonPath("$.name").value(example.getName()));
    }
}

