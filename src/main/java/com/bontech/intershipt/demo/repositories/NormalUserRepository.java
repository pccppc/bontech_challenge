package com.bontech.intershipt.demo.repositories;

import com.bontech.intershipt.demo.models.usr.NormalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NormalUserRepository extends JpaRepository<NormalUser,Long> {
}
