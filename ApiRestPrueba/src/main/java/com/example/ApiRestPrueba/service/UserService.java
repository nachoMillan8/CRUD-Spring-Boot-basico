package com.example.ApiRestPrueba.service;

import com.example.ApiRestPrueba.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    public Iterable<User> findAll();

    public Page<User> findAll(Pageable pageable);

    public Optional<User> findById(Long id);//SE PONE OPTIONAL PARA EVITAR UN NULL EN CASO DE NO ENCONTRAR UN USUARIO

    public User save(User user);

    public void deleteById(Long id);

}
