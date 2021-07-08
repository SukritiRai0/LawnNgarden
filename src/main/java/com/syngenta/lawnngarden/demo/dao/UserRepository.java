package com.syngenta.lawnngarden.demo.dao;

import com.syngenta.lawnngarden.demo.po.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassWord(String email, String passWord);
    User findByEmail(String email);
}
