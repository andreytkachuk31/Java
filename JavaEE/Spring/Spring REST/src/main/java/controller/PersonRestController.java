package controller;

import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.PersonService;

@Controller
@RequestMapping("/api")
public class PersonRestController {

    @Autowired
    private PersonService personService;

    @RequestMapping("person/{id}")
    public @ResponseBody Person getById(@PathVariable Long id) {
        return personService.getById(id);
    }

    @RequestMapping(value="person", method= RequestMethod.POST)
    public @ResponseBody String savePerson(@RequestBody Person person) {
        personService.save(person);
        return "Saved person: " + person.toString();
    }
}
