/* This is an implementation of postgres database */

package com.fractal.postgresdemo.dao;

import com.fractal.postgresdemo.exxception.PersonNotFoundException;
import com.fractal.postgresdemo.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.fractal.postgresdemo.model.Person;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAOImpl implements PersonDAO {

    JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_PERSON = "select * from people where id = ?";
    private final String SQL_GET_ALL = "select * from public.people";
    private final String SQL_INSERT_PERSON = "insert into people (first_name, last_name, age) values (?,?,?)";
    private final String SQL_UPDATE_PERSON = "update people set first_name = ?, last_name = ?, age = ? where id= ?";
    private final String SQL_DELETE_PERSON = "delete from people where id = ?";

    @Autowired
    public  PersonDAOImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Person getPersonById(Long Id) {
        return jdbcTemplate.queryForObject(SQL_FIND_PERSON, new Object[] { Id }, new PersonMapper());
    }

    public List<Person> getAllPersons(){
        return jdbcTemplate.query(SQL_GET_ALL, new PersonMapper());
    }

    public Long createPerson(Person person){
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection ->{
            PreparedStatement ps = connection
                    .prepareStatement(SQL_INSERT_PERSON, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, person.getFirstName());
                    ps.setString(2, person.getLastName());
                    ps.setInt(3,person.getAge());
                    return ps;
        }, keyHolder);
        //if(keyHolder.getKeys().size()>0)
        return (Long) keyHolder.getKeys().get("id");
    }

    public boolean updatePerson(Person person){
        return jdbcTemplate.update(SQL_UPDATE_PERSON, person.getFirstName(), person.getLastName(), person.getAge(), person.getId())>0;
    }

    public boolean deletePerson(Long id)  {
        return jdbcTemplate.update(SQL_DELETE_PERSON, id) >0;
    }
}
