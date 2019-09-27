package com.fractal.postgresdemo.mapper;

import com.fractal.postgresdemo.model.Person;
import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultset, int i) throws SQLException {

        Person person = new Person();
        person.setId(resultset.getLong("id"));
        person.setFirstName(resultset.getString("first_name"));
        person.setLastName(resultset.getString("last_name"));
        return  person;
    }
}
