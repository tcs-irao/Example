package com.example.Exampledemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExampleRestController {
    @Autowired
ExampleService exampleService;
@GetMapping("/examples")
public List<Example> getAllExample() {
    return exampleService.getAllExamples();
}
    @GetMapping("/examples/{id}")
    public Example getExampleById(@PathVariable int id){

        return exampleService.getExampleById(id);
    }
    @PostMapping("/examples/addexample")
    public Example addExample(@RequestBody Example example){
        return exampleService.createExample(example);
    }
    @PutMapping("/examples/updateexample")
    public Example updateExample(@RequestBody Example example){
        return exampleService.updateExample(example);
    }

    @DeleteMapping("/examples/delete/{id}")
    public void deleteExample(@PathVariable int id){
        System.out.println("Delete record id = "+id);
        exampleService.deleteById(id);
    }
    @DeleteMapping("/examples/delete")
    public Example deleteExample(@RequestBody Example example){
       return exampleService.deleteExample(example);
    }

}
