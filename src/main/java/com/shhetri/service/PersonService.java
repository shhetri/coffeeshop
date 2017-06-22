package com.shhetri.service;

import com.shhetri.exceptions.ModelNotFoundException;
import com.shhetri.model.Authority;
import com.shhetri.model.Person;
import com.shhetri.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PasswordEncoder passwordEncoder, PersonRepository personRepository) {
        this.passwordEncoder = passwordEncoder;
        this.personRepository = personRepository;
    }

    public Person savePerson(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        Authority authority = new Authority(person, "USER");
        person.setAuthorities(Collections.singletonList(authority));

        return personRepository.save(person);
    }

    public Person updatePerson(Person person, int id) throws ModelNotFoundException {
        Person originalPerson = findById(id);
        originalPerson.setEmail(person.getEmail());
        originalPerson.setEnabled(person.isEnabled());
        originalPerson.setFirstName(person.getFirstName());
        originalPerson.setLastName(person.getLastName());
        originalPerson.setPhone(person.getPhone());
        originalPerson.getAddress().setCountry(person.getAddress().getCountry());
        originalPerson.getAddress().setZipcode(person.getAddress().getZipcode());
        originalPerson.getAddress().setCity(person.getAddress().getCity());
        originalPerson.getAddress().setState(person.getAddress().getState());

        return personRepository.save(originalPerson);
    }

    public Person findByEmail(String email) throws ModelNotFoundException {
        return personRepository.findFirstByEmail(email).orElseThrow(
                () -> new ModelNotFoundException(Person.class.getSimpleName(), "email", email)
        );
    }

    public Person findById(int id) throws ModelNotFoundException {
        return personRepository.findFirstById(id).orElseThrow(
                () -> new ModelNotFoundException(Person.class.getSimpleName(), id)
        );
    }

    public void removePerson(Person person) {
        personRepository.delete(person);
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }
}
