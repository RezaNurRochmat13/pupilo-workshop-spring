package com.pupilo.MainApplication.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@RestController
@RequestMapping("/api/")
public class PersonController {
    
    @GetMapping("persons")
    public ResponseEntity<Object> getAllPersons() {
        List<String> persons = new ArrayList<>();
        persons.add("Fikri");
        persons.add("Amien");
        persons.add("Indra");

        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("persons/{id}")
    public ResponseEntity<Object> getPersonById(@PathVariable Integer id) {
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("persons")
    public ResponseEntity<Object> createPerson(@RequestBody String payload) {
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }

    @PutMapping("persons/{id}")
    public ResponseEntity<Object> updatePerson(@RequestBody String payload, @PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("payload", payload);
        map.put("id", id);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("persons/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable Integer id) {
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
