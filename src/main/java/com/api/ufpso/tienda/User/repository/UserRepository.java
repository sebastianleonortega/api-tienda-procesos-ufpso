package com.api.ufpso.tienda.User.repository;

import com.api.ufpso.tienda.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail (String email);
}
