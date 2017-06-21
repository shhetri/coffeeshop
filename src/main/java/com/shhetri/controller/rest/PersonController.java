package com.shhetri.controller.rest;

import com.shhetri.exceptions.ModelNotFoundException;
import com.shhetri.model.Person;
import com.shhetri.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("")
    public List<Person> index() {
        return personService.getAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Person store(@Valid @RequestBody Person person) {
        return personService.savePerson(person);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person update(@Valid @RequestBody Person person, @PathVariable int id) throws ModelNotFoundException {
        return personService.updatePerson(person, id);
    }
}
