package com.bontech.intershipt.demo.repositories;

import com.bontech.intershipt.demo.models.db.NormalUserService;
import com.bontech.intershipt.demo.models.db.NormalUserServiceId;
import com.bontech.intershipt.demo.models.db.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NormalUserServiceRepository extends JpaRepository<NormalUserService, NormalUserServiceId> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from  user_service as nus where nus.user_id = ?1")
    void deleteAllByUserId(Long userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into user_service (user_id,service_id) values(?1,?2)")
    void grantServiceForUser(Long userId,Long serviceId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from  user_service as nus where nus.user_id = ?1 , nus.service_id = ?2 ")
    void revokeServiceForUser(Long userId,Long serviceId);

    @Query(nativeQuery = true, value = "select nus.service_id from  user_service as nus where nus.user_id = ?1")
    List<Long> findAllByUserId(Long userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update user_service as nus set nus.number_of_usage = nus.number_of_usage + 1 where nus.user_id = ?1 and nus.service_id = ?2")
    void increaseNumberOfUsage(Long userId,Long ServiceId);

    @Query(nativeQuery = true, value = "select nus.number_of_usage from user_service as nus where nus.user_id = ?1 and nus.service_id = ?2 ")
    Long getNumberOfUsageByUserIdAndServiceId(Long userId,Long serviceId);
}
