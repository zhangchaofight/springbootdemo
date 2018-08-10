package com.example.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRespository extends JpaRepository<Login, String> {

    Login findByToken(String token);
}
