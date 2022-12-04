package com.pupilo.MainApplication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pupilo.MainApplication.entity.Person;
import com.pupilo.MainApplication.exception.ResourceNotFoundException;
import com.pupilo.MainApplication.repository.PersonRepository;

@RestController
@RequestMapping("/api/")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;
    
    @GetMapping("persons")
    public ResponseEntity<Object> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put("data", persons);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("persons/{id}")
    public ResponseEntity<Object> getPersonById(@PathVariable Long id) {
        Person personById = personRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Person not found with id : " + id));
        
        return new ResponseEntity<>(personById, HttpStatus.OK);
    }

    @PostMapping("persons")
    public ResponseEntity<Object> createPerson(@RequestBody Person payload) {
        return new ResponseEntity<>(personRepository.save(payload), HttpStatus.CREATED);
    }

    @PutMapping("persons/{id}")
    public ResponseEntity<Object> updatePerson(@RequestBody Person payload, @PathVariable Long id) {
        Person personById = personRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Person not found with id : " + id));

        personById.setName(payload.getName());
        personById.setAge(payload.getAge());
        personById.setAddress(payload.getAddress());

        return new ResponseEntity<>(personRepository.save(personById), HttpStatus.OK);
    }

    @DeleteMapping("persons/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable Long id) {
        Person personById = personRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Person not found with id : " + id));

        personRepository.delete(personById);

        return new ResponseEntity<>("Successfullly delete", HttpStatus.NO_CONTENT);
    }
}
