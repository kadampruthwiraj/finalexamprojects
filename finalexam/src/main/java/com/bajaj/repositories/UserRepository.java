package com.bajaj.repositories;


import com.bajaj.entities.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserDetailsEntity,Long> {


    List<UserDetailsEntity> findByEmail(String email);



    List<UserDetailsEntity> findByEmailAndPassword(String email, String password);


}
