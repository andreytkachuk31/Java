package controller;

import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.PersonService;

@Controller
@RequestMapping("/api")
public class PersonRestController {

    @Autowired
    private PersonService personService;

    @RequestMapping("person/{id}")
    public Person getById(@PathVariable Long id) {
        return personService.getById(id);
    }

    @RequestMapping(value="person", params="id")
    public Person getByIdFromParam(@RequestParam("id") Long id) {
        return personService.getById(id);
    }


    @RequestMapping(value="person", method= RequestMethod.POST)
    public String savePerson(Person person) {
        personService.save(person);
        return "Saved person: " + person.toString();
    }
}
