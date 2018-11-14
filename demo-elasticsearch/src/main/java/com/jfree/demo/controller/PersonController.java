package com.jfree.demo.controller;

import com.jfree.demo.entity.Person;
import com.jfree.demo.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonRepo personRepo;

    @GetMapping("/add")
    public Person add(Person p) {
        personRepo.save(p);
        return p;
    }

    @GetMapping("/search")
    public List<Person> search(String sex) {
        return personRepo.findBySex(sex);
    }
}
