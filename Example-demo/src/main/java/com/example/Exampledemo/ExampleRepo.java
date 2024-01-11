package com.example.Exampledemo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepo extends JpaRepository<Example,Integer> {
}
