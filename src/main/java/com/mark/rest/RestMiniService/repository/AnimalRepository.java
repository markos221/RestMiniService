package com.mark.rest.RestMiniService.repository;

import com.mark.rest.RestMiniService.domain.Animal;
import com.mark.rest.RestMiniService.domain.TypeAnimal;
import com.mark.rest.RestMiniService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends MongoRepository<Animal, String> {

    List<Animal> findByUsers(User user);
    Animal findOneByType(TypeAnimal typeAnimal);
    Optional<Animal> findById(String id);
}
