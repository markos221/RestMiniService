package com.mark.rest.RestMiniService.controller;

import com.mark.rest.RestMiniService.domain.Animal;
import com.mark.rest.RestMiniService.domain.User;
import com.mark.rest.RestMiniService.repository.AnimalRepository;
import com.mark.rest.RestMiniService.service.AnimalService;
import com.mark.rest.RestMiniService.service.AnimalUserService;
import com.mark.rest.RestMiniService.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/animal")
@Secured("hasRole('ADMIN')")
public class AnimalRESTController {

    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    AnimalRepository repository;
    @Autowired
    AnimalService animalService;
    @Autowired
    AnimalUserService animalUserService;
    @Autowired
    CustomUserDetailsService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Animal> getAllAnimal() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Animal getAnimalById(@PathVariable("id") String id, Animal animal) {
        return animalService.getAnimal(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateAnimalById(@PathVariable("id") String id, @Valid @RequestBody Animal animal) {
        animal.setId(id);
        animalService.add(animal);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Animal createAnimal(@Valid @RequestBody Animal animal) {
        animalService.add(animal);
        return animal;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAnimal(@PathVariable String id) {
        repository.delete(animalService.getAnimal(id));
    }

    /*@PutMapping("/animal/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable("id") String id, @RequestBody Animal animal) {
        System.out.println("Update Animal with ID = " + id + "...");

        Optional<Animal> animalData = animalRepository.findById(id);

        if (animalData.isPresent()) {
            Animal _animal = animalData.get();
            _animal.setName(animal.getName());
            _animal.setAge(animal.getAge());
            _animal.setType(animal.getType());
            return new ResponseEntity<>(animalService.add(_animal), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/


}
