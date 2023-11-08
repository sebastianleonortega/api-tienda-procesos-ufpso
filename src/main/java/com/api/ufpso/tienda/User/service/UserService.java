package com.api.ufpso.tienda.User.service;

import com.api.ufpso.tienda.User.model.User;
import com.api.ufpso.tienda.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
        throw new RuntimeException("No existe el usuario");
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

}
