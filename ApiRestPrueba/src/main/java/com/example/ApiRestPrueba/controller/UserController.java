package com.example.ApiRestPrueba.controller;


import com.example.ApiRestPrueba.entity.User;
import com.example.ApiRestPrueba.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController//COMBINACION ENTRE LAS ANOTACIONES RESPONSEBODY Y CONTROLLER
@RequestMapping("/api/users")//ESTABLECEMOS LA URL POR LA QUE SE TRAMITARAN LAS CONSULTAS
public class UserController {



    //INYECTAMOS ESTA DEPENDENCIA PARA TENER ACCESO A LOS METODOS DE ESTA INTERFAZ QUE SON LOS QUE HACEN LAS CONSULTAS
    @Autowired
    private UserService userService;



    // CREAR NUEVO USUARIO
    //POST SIRVE PARA CREAR, EQUIVALDRIA A HACER UN REQUESTMAPPING ESPECIFICANDO EL POST
    @PostMapping
    public ResponseEntity<?> create (@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }




    //LEER USUARIO
    //GET SIRVE PARA LEER
    @GetMapping("/{id}")// A LA HORA DE BUSCAR EL USUARIO LO HARA POR SU ID
    public ResponseEntity<?> read(@PathVariable Long id) {

        //COMPROBAMOS SI EXISTE EL ID
        Optional<User> optionalUser=userService.findById(id);
        if (!optionalUser.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalUser);
    }



    //ACTUALIZAR USUARIO(PUT)
    @PutMapping("/{id}")
    //EN ESTE CASO SE COMBINA LA CONSULTA CON AÑADIR POR ESO USAMOS LAS DOS ANOTACIONES DE REQUEST BODY Y PATHVARIABLE
    public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable Long id) {
        //CREAMOS EL OBJETO OPTIONAL USER QUE SERIA EL DE LA "LECTURA" DE USUARIO
        Optional<User> optionalUser=userService.findById(id);
        //COMPROBAMOS SI EXISTE
        if (!optionalUser.isPresent()){
            return ResponseEntity.notFound().build();
        }
        //AQUI INSERTAMOS LOS NUEVOS VALORES DEL USERDETAILS EN TODOS LOS CAMPOS DEL USUARIO LEIDO
        optionalUser.get().setNombre(userDetails.getNombre());
        optionalUser.get().setApellido(userDetails.getApellido());
        optionalUser.get().setEmail(userDetails.getEmail());
        optionalUser.get().setOperativo(userDetails.getOperativo());

        // BeanUtils.copyProperties(userDetails,optionalUser);
        // CON ESTO SUSTITUIMOS ATUTOMATICAMENTE LOS CAMPOS MODIFICADOS EN USERDETAILS EN LOS CAMPOS LEIDOS MUY UTIL PARA CUANDO HAY MUCHOS CAMPOS

        //DEVOLVEMOS QUE SE HA CREADO CORRECTAMENTE Y LO GUARDAMOS
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(optionalUser.get()));
    }




    //BORRAR USUARIO
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        //COMPROBAMOS CON EL OBJETO OPTIONAL SI EXISTE
        Optional<User> optionalUser=userService.findById(id);
        if (!optionalUser.isPresent()){
            return ResponseEntity.notFound().build();
        }

        //Y SI EXISTE SE BORRA EL USUARIO DEL ID DE LA CONSULTA
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }


    //LEER TODOS LOS USUARIOS(SELECT * FROM USER)
    @GetMapping
    public List<User> readAll() {
        List<User> usuarios = StreamSupport
                .stream(userService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return usuarios;
    }
}
