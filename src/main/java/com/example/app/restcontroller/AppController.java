/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.app.restcontroller;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 *
 * @author razamd
 */
@RestController
@RequestMapping("/")
public class AppController {
    
    @GetMapping
    public ResponseEntity<?> root(){
        ResourceSupport resources = new ResourceSupport();
        ControllerLinkBuilder linkBuilder = linkTo(AppController.class).slash("api");
        resources.add(linkBuilder.slash("users").withRel("users"));
        return ResponseEntity.ok(resources);
    }
}
