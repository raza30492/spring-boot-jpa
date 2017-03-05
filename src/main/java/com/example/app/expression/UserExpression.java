/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.app.expression;

import com.example.app.entity.QUser;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 *
 * @author razamd
 */
public class UserExpression {

    public UserExpression() {
    }
    
    public static BooleanExpression hasEmail(String email){
        return QUser.user.email.eq(email);
        //return QEmployee.employee.email.eq(email);
    }
    
    public static BooleanExpression hasPassword(String password){
        return QUser.user.password.eq(password);
    }
}
