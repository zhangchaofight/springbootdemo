package com.example.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByAccount(String account);

    User findByUserId(long id);

    List<User> findAllByAccount(String account);

    long countByAccount(String account);
}