package com.mark.rest.RestMiniService.service;

import com.mark.rest.RestMiniService.domain.Animal;
import com.mark.rest.RestMiniService.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class AnimalUserService {
    @Autowired
    CustomUserDetailsService userService;
    @Autowired
    AnimalService animalService;


    public Animal animalUserS(Animal animal, User user){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User users = userService.findUserByUsername(auth.getName());
        //User userAnimal = userRepository.findByUsername(auth.getName());
        animal.setUsers (new HashSet<User>(Arrays.asList(users)));
        animalService.findByUsers(user);
        if(user == users){
            return animal;
        }

        return null;
    }
}
