package service;

import model.Person;

public interface PersonService {

    Person getById(Long id);

    void save(Person person);

}
