package com.api.ufpso.tienda.User.service;

import com.api.ufpso.tienda.User.model.User;
import com.api.ufpso.tienda.User.repository.UserRepository;
import com.api.ufpso.tienda.exception.NotFoundException;
import com.api.ufpso.tienda.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return  userRepository.findById(id).get();
    }

    public User updateUser(User user, Long id) {
        Optional<User> userExist = userRepository.findById(id);
        if (userExist.isPresent()) {
            User existingUser = userExist.get();
            existingUser.setLastName(user.getLastName());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setDocument(user.getDocument());
            existingUser.setPhone(user.getPhone());
            return userRepository.save(existingUser);
        }
        throw new NotFoundException("No existe el usuario");
    }

    public Boolean delete(Long id){
        Optional<User> userExist = userRepository.findById(id);
        if(userExist.isEmpty()){
            return false;
        }
        userRepository.delete(userExist.get());
        return true;
    }

    public List<User> findAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    public ResponseEntity login(String email, String password) {
        try {
            User user = userRepository.findByEmail(email);

            if (user != null && Objects.equals(password, user.getPassword())) {
                String token = jwtUtil.create(String.valueOf(user.getId()), user.getEmail());
                return ResponseEntity.ok(token);
            } else {
                // User not found or password doesn't match
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas, vuelva a intentar");
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error durante el login");
        }
    }

}
