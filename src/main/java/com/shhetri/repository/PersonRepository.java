package com.shhetri.repository;

import com.shhetri.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    public Optional<Person> findFirstByEmail(String email);
    public Optional<Person> findFirstByEmailAndIdNot(String email, int id);

    public Optional<Person> findFirstById(int id);
}
