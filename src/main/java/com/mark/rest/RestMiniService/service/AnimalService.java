package com.mark.rest.RestMiniService.service;

import com.mark.rest.RestMiniService.domain.Animal;
import com.mark.rest.RestMiniService.domain.User;
import com.mark.rest.RestMiniService.repository.AnimalRepository;
import com.mark.rest.RestMiniService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailsService userService;


    public AnimalRepository getAnimalRepository() {
        return animalRepository;
    }

    public void setAnimalRepository(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public void add(Animal animal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        //User userAnimal = userRepository.findByUsername(auth.getName());
        animal.setUsers (new HashSet<User>(Arrays.asList(user)));
        animalRepository.save(animal);
    }

    public List<Animal> findByUsers(User user)
    {
        return animalRepository.findByUsers(user);
    }

    public Animal getAnimal(String id) {


            return animalRepository.findById(id).get();

    }

    public void removeAnimal(String id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        animalRepository.findByUsers(user);
        animalRepository.deleteById(id);
    }

}
