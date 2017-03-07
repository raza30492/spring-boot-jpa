/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.app.restcontroller;

import com.example.app.assembler.UserAssembler;
import com.example.app.entity.User;
import com.example.app.service.UserService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
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

/**
 *
 * @author razamd
 */
@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired UserService userService;
    
    @Autowired UserAssembler userAssembler;

    @GetMapping(value = {"/{userId}"})
    public ResponseEntity<?> getUser(@PathVariable("userId") Long id) {
        User user = userService.findOne(id);
        return ResponseEntity.ok(userAssembler.toResource(user));
    }
    
    @GetMapping
    public PagedResources<User> getAllUsers(Pageable pageable, PagedResourcesAssembler assembler) {
        Page<User> page = userService.findAllByPage(pageable);
        return assembler.toResource(page, userAssembler);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        user = userService.save(user);
        Link selfLink = linkTo(UserRestController.class).slash(user.getId()).withSelfRel();
        return ResponseEntity.created(URI.create(selfLink.getHref())).build();
    }
    
//    @GetMapping
//    public ResponseEntity<?> getAllUsers() {
//        List<User> users = userService.findAll();
//        List<Resource> resources = userAssembler.toResources(users);
//        return ResponseEntity.ok(new Resources<>(resources, linkTo(UserRestController.class).withSelfRel()));
//    }
//
//    private Resources<Resource<User>> userToResources(List<User> users) {
//        Link selfLink = linkTo(methodOn(UserRestController.class).getAllUsers()).withSelfRel();
//        List<Resource<User>> userResources = users.stream().map(user -> userToResource(user)).collect(Collectors.toList());
//        return new Resources<>(userResources, selfLink);
//    }
//
//    private Resource<User> userToResource(User user) {
//        Link selfLink = linkTo(methodOn(UserRestController.class).getUser(user.getId())).withSelfRel();
//        return new Resource<>(user, selfLink);
//    }
    
}
