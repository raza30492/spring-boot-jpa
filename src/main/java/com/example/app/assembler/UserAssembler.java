/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.app.assembler;

import com.example.app.entity.User;
import com.example.app.restcontroller.UserRestController;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 *
 * @author razamd
 */
@Component
public class UserAssembler extends ResourceAssemblerSupport<User, Resource>{
    
    public UserAssembler(){
        super(UserRestController.class, Resource.class);
    }

    @Override
    public Resource toResource(User user) {
        return new Resource<>(user, linkTo(methodOn(UserRestController.class).getUser(user.getId())).withSelfRel());
    }

    @Override
    public List<Resource> toResources(Iterable<? extends User> users) {
        List<Resource> resources = new ArrayList<>();
        for(User user : users) {
            resources.add(new Resource<>(user, linkTo(UserRestController.class).slash(user.getId()).withSelfRel()));
        }
        return resources;
    }
    
    
    
}
