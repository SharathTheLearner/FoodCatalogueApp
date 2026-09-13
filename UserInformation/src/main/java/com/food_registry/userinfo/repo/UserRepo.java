package com.food_registry.userinfo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food_registry.userinfo.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
