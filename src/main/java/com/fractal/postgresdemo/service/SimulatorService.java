package com.fractal.postgresdemo.service;

import com.fractal.postgresdemo.dao.PersonDAO;
import com.fractal.postgresdemo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimulatorService {

    @Autowired
    private PersonDAO personDAO;

    public SimulatorService(){
        super();
    }

    public List<Person> getAllPersons(){
        return personDAO.getAllPersons();
    }
    public Person getPersonById(Long id) {return personDAO.getPersonById(id); }
    public Long addPerson(Person person) { return personDAO.createPerson(person); }
    public boolean deletePerson(Long id) { return personDAO.deletePerson(id); }
    public boolean updatePerson(Person person) { return personDAO.updatePerson(person); }
}
