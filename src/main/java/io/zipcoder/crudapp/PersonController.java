package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    List<Person> getPersonList() {
        List<Person> people = new ArrayList<>();
        personRepository.findAll().forEach(people::add);
        return people;
    }

    @GetMapping("/{id}")
    Person getPerson(@PathVariable long id) {
        return personRepository.findOne(id);
    }

    @PostMapping
    Person createPerson(@RequestBody Person p) {
        return personRepository.save(p);
    }

    @PutMapping("/{id}")
    Person updatePerson(@PathVariable long id, @RequestBody Person p) {
        Person orig = personRepository.findOne(id);
        orig.setFirstName(p.getFirstName());
        orig.setLastName(p.getLastName());
        return personRepository.save(orig);
    }

    @DeleteMapping("/{id}")
    String DeletePerson(@PathVariable long id) {
        String response = personRepository.findOne(id).getFirstName() + " Deleted";
        personRepository.delete(id);
        return response;
    }

}
