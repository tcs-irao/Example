package com.example.Exampledemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExampleService {
    @Autowired
    ExampleRepo exampleRepo;
    public List<Example> getAllExamples(){

        return exampleRepo.findAll();
    }
    public Example getExampleById(int id){

        return exampleRepo.findById(id).orElse(null);
    }
    public Example createExample(Example example){
        exampleRepo.save(example);
        return  example;
    }
    public Example updateExample(Example example){
        Example existingExample = exampleRepo.findById(example.getId()).orElse(null);
        if(null != existingExample)
        {
            existingExample.setName(example.getName());
            existingExample.setModel(example.getModel());
            existingExample.setPlace(example.getPlace());
            existingExample.setWarranty(example.getWarranty());
            return exampleRepo.save(existingExample);
        }
        return null;
    }

    public void deleteById(int id){

        exampleRepo.deleteById(id);
    }
    public Example deleteExample(Example example){
        return exampleRepo.save(example);
    }
}
