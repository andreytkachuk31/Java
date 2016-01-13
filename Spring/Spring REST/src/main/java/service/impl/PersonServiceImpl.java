package service.impl;

import model.Person;
import org.springframework.stereotype.Service;
import service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

    private static final String[] NAMES = {"Nikolaus Otto", "Robert Ford", "Gottlieb Daimler", "Lt. General Masaharu Homma"};

    @Override
    public Person getById(Long id) {
        Person person = new Person();
        person.setName(NAMES[id.intValue()]);
        person.setAge(50);
        return person;
    }

    @Override
    public void save(Person person) {
        // Save person to database ...
    }
}
