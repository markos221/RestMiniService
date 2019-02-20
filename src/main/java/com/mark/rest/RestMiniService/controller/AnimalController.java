package com.mark.rest.RestMiniService.controller;

import com.mark.rest.RestMiniService.domain.Animal;
import com.mark.rest.RestMiniService.domain.User;
import com.mark.rest.RestMiniService.repository.AnimalRepository;
import com.mark.rest.RestMiniService.service.AnimalService;
import com.mark.rest.RestMiniService.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@RestController
@Secured("hasRole('ADMIN')")
public class AnimalController {

    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    AnimalService animalService;
    @Autowired
    CustomUserDetailsService userService;


    public AnimalService getAnimalService() { return animalService; }

    public void setAnimalService(AnimalService animalService) { this.animalService = animalService; }

    @RequestMapping(value = "/dashboard", method = RequestMethod.POST)
    public ModelAndView addAnimal(@ModelAttribute("animal") Animal animal) {
        if (animal.getId() == null) animalService.add(animal);
        else animalService.add(animal);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }

    @PutMapping("/update/{id}")
    public void updateAnimal(@RequestBody Animal animal, @PathVariable String id) {
        Optional<Animal> animals = animalRepository.findById(id);
        return;
    }

    @GetMapping("/showFormForUpdate")
    public ModelAndView showFormForUpdate(@RequestParam("id") String id, Model model){

        // Get the cake by id
        Animal animal = animalService.getAnimal(id);

        // set the cake as a model attribute and send it to the view to pre-populate
        model.addAttribute("animal" , animal);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-animal");

        return modelAndView;
    }

    @GetMapping("/showFormForAdd")
    public ModelAndView showFormForAdd(Model model){

        Animal animal = new Animal();

        model.addAttribute("animal" , animal);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-animal");
        return modelAndView;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id, Model model){

        animalService.removeAnimal(id);

        return "redirect:/dashboard";
    }

    @GetMapping("/delete/{id}")
    public Animal getA(@PathVariable String id, Animal animals, User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User users = userService.findUserByUsername(auth.getName());
        //User userAnimal = userRepository.findByUsername(auth.getName());
        animals.setUsers (new HashSet<User>(Arrays.asList(users)));
        animalService.findByUsers(user);
    Animal animal = animalService.getAnimal(id);

    if (animal == null) {
        throw new RuntimeException("Cake not found : " + id);
        }
    return animal;
    }


}
