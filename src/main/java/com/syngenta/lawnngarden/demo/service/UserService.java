package com.syngenta.lawnngarden.demo.service;

import com.syngenta.lawnngarden.demo.dao.UserRepository;
import com.syngenta.lawnngarden.demo.po.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User checkUser(String email, String passWord){
        
        User user = userRepository.findByEmailAndPassWord(email, passWord);
        return user;
    }

    @Transactional
    public User findUser(String email){
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Transactional
    public User saveUser(User user){
        return userRepository.save(user);
    }


}
