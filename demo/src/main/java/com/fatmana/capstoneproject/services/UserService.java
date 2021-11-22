package com.fatmana.capstoneproject.services;
//you guessed it up we can handle the registration so that we can actually save and update

import com.fatmana.capstoneproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



}