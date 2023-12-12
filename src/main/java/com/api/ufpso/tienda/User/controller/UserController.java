package com.api.ufpso.tienda.User.controller;

import com.api.ufpso.tienda.User.model.User;
import com.api.ufpso.tienda.User.repository.UserRepository;
import com.api.ufpso.tienda.User.service.UserService;
import com.api.ufpso.tienda.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id, @RequestHeader(value = "Authorization")String token){
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        User user = userService.getUserById(id);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    };

    @PostMapping("/register")
    public ResponseEntity<?> Create(@Valid @RequestBody User user){
        User userdb = userRepository.findByEmail(user.getEmail());
        if(userdb != null)
            return ResponseEntity.badRequest().body("Correo ya existente");
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  update(@Valid @RequestBody User user, @PathVariable Long id, @RequestHeader(value = "Authorization")String token){
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        if (user != null) {
            return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>  delete(@PathVariable Long id,  @RequestHeader(value = "Authorization")String token){
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        return new ResponseEntity(userService.delete(id), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(@RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token) == null) {
            // Devuelve un objeto JSON con el mensaje de error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Token no válido"));
        }

        // Si el token es válido, devuelve la lista de usuarios
        return ResponseEntity.ok(userService.findAllUsers());
    }




    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @PostMapping(value = "/auth/login")
    public ResponseEntity login(@RequestBody User user) {
        return ResponseEntity.ok(userService.login(user.getEmail(), user.getPassword()));
    }
}

