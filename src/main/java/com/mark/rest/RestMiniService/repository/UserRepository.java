
package com.mark.rest.RestMiniService.repository;

import com.mark.rest.RestMiniService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    
    User findByUsername(String username);

}
