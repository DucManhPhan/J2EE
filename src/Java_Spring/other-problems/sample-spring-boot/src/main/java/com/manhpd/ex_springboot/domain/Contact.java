/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manhpd.ex_springboot.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author manh.phanduc
 */
@Entity
@Table(name = "contact")
public class Contact implements Serializable{
    private static final long serialVersionUID = 2L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    
    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;
    
    @Email
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone")
    private String phone;
    
    public Contact() {
        super();
    }
    
    public Contact(int id, String name, String email, String phone) {
        super();
        this.id = id; 
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    public int getId() {
        return id;
    } 
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    } 
    
    public void setName(String name) {
    this.name = name;
    } 
    public String getEmail() {
        return email;
    } 
    
    public void setEmail(String email) {
        this.email = email;
    } 
    
    public String getPhone() {
        return phone;
    } 
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
