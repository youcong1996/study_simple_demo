package com.ssh.service;

import com.ssh.entity.Person;

/**
 * Created by XRog
 * On 2/2/2017.2:39 PM
 */
public interface PersonService {
    Long savePerson();
    
    Person getId(Long id);
    
    void delete(Long id);
}