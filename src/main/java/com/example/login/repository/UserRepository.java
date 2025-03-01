package com.example.login.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.login.entity.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
