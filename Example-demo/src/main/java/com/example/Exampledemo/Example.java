package com.example.Exampledemo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="example")
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String model;
    private String place;
    private int warranty;
}
