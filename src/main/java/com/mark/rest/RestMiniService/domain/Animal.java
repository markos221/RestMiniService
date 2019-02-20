package com.mark.rest.RestMiniService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = Animal.COLLECTION_NAME)
public class Animal {

    public static final String COLLECTION_NAME = "animals";

    @Id
    @Field("id")
    private String id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String name;
    private String age;
    private String gender;
    @DBRef(lazy = true)
    private List<TypeAnimal> typeAnimal;
    @DBRef(lazy = true)
    private Set<User> users;

    public Animal(){
    }

    public Animal(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAge() { return age; }

    public void setAge(String age) { this.age = age; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public Set<User> getUsers(HashSet<User> users) {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public List<TypeAnimal> getType() { return typeAnimal; }

    public void setType(List<TypeAnimal> typeAnimal) { this.typeAnimal = typeAnimal; }



    /*public String toString(){
        String info = String.format("{'id': %s 'name': %s, 'age': %d}", id, name, age);
        return info;
    }*/

}
