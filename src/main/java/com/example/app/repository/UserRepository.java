/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.app.repository;

import com.example.app.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 *
 * @author razamd
 */
public interface UserRepository extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User>{
    Optional<User> findById(Long id);
}
