/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.app.service;

import com.example.app.entity.User;
import static com.example.app.expression.UserExpression.*;
import com.example.app.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author razamd
 */
@Service
@Transactional
public class UserService {
    
    @Autowired UserRepository userRepository;
    
    public User save(User user){
        return userRepository.save(user);
    }
    
    public User authenticate(String email, String password){
        return userRepository.findOne(hasEmail(email).and(hasPassword(password)));
    }
    
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
    
    public User findOne(Long id){
        return userRepository.findOne(id);
    }
    
    public List<User> findAll(){
        return userRepository.findAll();
    }
    
    public void delete(Long id){
        userRepository.delete(id);
    }
    
    public void update(User user){
        User user2 = userRepository.findOne(user.getId());
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
    }
    
}
