package com.bontech.intershipt.demo.repositories;

import com.bontech.intershipt.demo.models.usr.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
