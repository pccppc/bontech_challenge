package com.bontech.intershipt.demo.repositories;

import com.bontech.intershipt.demo.models.db.usr.NormalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NormalUserRepository extends JpaRepository<NormalUser,Long> {

    @Transactional
    @Modifying
    @Query(value = "update NormalUser u set u.username = ?1 ,u.password = ?2 where u.id = ?3")
    void updateUserById(String username,String password,Long id);


    @Transactional
    @Modifying
    @Query(value = "delete from NormalUser where id = ?1")
    void deleteNormalUserById(Long id);

    @Transactional
    @Modifying
    @Query(value = "update NormalUser u set u.balance = u.balance + ?1 where u.id = ?2")
    void increaseBalance(Long amount, Long userId);


    @Transactional
    @Modifying
    @Query(value = "update NormalUser u set u.balance =  ?1 where u.id = ?2")
    void setBalance(Long amount, Long userId);

    boolean existsById(Long id);
}
