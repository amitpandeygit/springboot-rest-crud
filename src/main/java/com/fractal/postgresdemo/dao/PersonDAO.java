package com.fractal.postgresdemo.dao;

import java.util.List;
import com.fractal.postgresdemo.model.Person;

public interface PersonDAO {
    Person getPersonById(Long Id);
    List<Person> getAllPersons();
    Long createPerson(Person person);
    boolean updatePerson(Person person);
    boolean deletePerson(Long id);
}
