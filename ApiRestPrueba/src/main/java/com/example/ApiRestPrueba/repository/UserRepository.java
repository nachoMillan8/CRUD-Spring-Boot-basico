package com.example.ApiRestPrueba.repository;


import com.example.ApiRestPrueba.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



//ESTA INTERFAZ SOLO SIRVE PARA USAR LOS METODOS DE JPA REPOSITORY EN LOS SERVICIOS
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
