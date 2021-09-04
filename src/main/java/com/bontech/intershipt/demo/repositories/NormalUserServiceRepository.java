package com.bontech.intershipt.demo.repositories;

import com.bontech.intershipt.demo.models.NormalUserService;
import com.bontech.intershipt.demo.models.NormalUserServiceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface NormalUserServiceRepository extends JpaRepository<NormalUserService, NormalUserServiceId> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from NormalUserService as nus where nus.user_id = ?1")
    void deleteAllByUserId(Long userId);
}
