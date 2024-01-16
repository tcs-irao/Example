package com.example.Exampledemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ExampleServiceTest {

    @Mock
    private ExampleRepo exampleRepo;

    @InjectMocks
    private ExampleService exampleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllExamples() {
        // Arrange
        List<Example> exampleList = new ArrayList<>();
        exampleList.add(new Example(1, "Lenovo", "LU58601", "Desktop", "2025"));
        exampleList.add(new Example(2, "Lenovo", "LA89510", "Desktop", "2026"));

        when(exampleRepo.findAll()).thenReturn(exampleList);

        // Act
        List<Example> result = exampleService.getAllExamples();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void getExampleById() {
        // Arrange
        int exampleId = 1;
        Example example = new Example(exampleId, "Lenovo", "LU58601", "Desktop", "2025");

        when(exampleRepo.findById(exampleId)).thenReturn(Optional.of(example));

        // Act
        Example result = exampleService.getExampleById(exampleId);

        // Assert
        assertEquals(example, result);
    }

    @Test
    void createExample() {
        // Arrange
        Example example = new Example(1, "Lenovo", "LU58601", "Desktop", "2025");

        // Act
        exampleService.createExample(example);

        // Assert
        verify(exampleRepo, times(1)).save(example);
    }

    @Test
    void updateExample() {
        // Arrange
        Example existingExample = new Example(1, "Lenovo", "LU58601", "Desktop", "2025");
        Example updatedExample = new Example(1, "Lenovo", "LU58601", "Desktop", "2025");

        when(exampleRepo.findById(existingExample.getId())).thenReturn(Optional.of(existingExample));
        when(exampleRepo.save(existingExample)).thenReturn(updatedExample);

        // Act
        Example result = exampleService.updateExample(updatedExample);

        // Assert
        assertEquals(updatedExample, result);
    }

    @Test
    void deleteById() {
        // Arrange
        int exampleId = 1;

        // Act
        exampleService.deleteById(exampleId);

        // Assert
        verify(exampleRepo, times(1)).deleteById(exampleId);
    }

    @Test
    void deleteExample() {
        // Arrange
        Example example = new Example(1, "Lenovo", "LU58601", "Desktop", "2025");

        // Act
        exampleService.deleteExample(example);

        // Assert
        verify(exampleRepo, times(1)).save(example);
    }
}
