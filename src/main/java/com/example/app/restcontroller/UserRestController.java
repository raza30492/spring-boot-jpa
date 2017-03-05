/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.app.restcontroller;

import com.example.app.entity.User;
import com.example.app.service.UserService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author razamd
 */
@RestController
@RequestMapping("/users")
public class UserRestController {
    
    @Autowired UserService userService;
    
    @GetMapping(value = {"/{userId}"})
    ResponseEntity<?> getUser(@PathVariable("userId") Long id){
        User user = userService.findOne(id);
        return ResponseEntity.ok(userToResource(user));
    }
    
    @GetMapping
    ResponseEntity<?> getAllUsers(){
        List<User> userList = userService.findAll();
        return ResponseEntity.ok(userToResources(userList));
    }
    
    @PostMapping
    ResponseEntity<?> save(@RequestBody User user){
        System.out.println(user);
       user = userService.save(user);
       
        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(user.getId()).toUri();
       
       return ResponseEntity.created(location).build();
    }
    
    private Resources<Resource<User>> userToResources(List<User> users){
        Link selfLink = linkTo(methodOn(UserRestController.class).getAllUsers()).withSelfRel();
        List<Resource<User>> userResources = users.stream().map(user -> userToResource(user)).collect(Collectors.toList());
        return new Resources<>(userResources, selfLink);
    }
    
    private Resource<User> userToResource(User user){
        Link selfLink = linkTo(methodOn(UserRestController.class).getUser(user.getId())).withSelfRel();
        return new Resource<>(user, selfLink);
    }
  
}
