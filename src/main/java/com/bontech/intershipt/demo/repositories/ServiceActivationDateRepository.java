package com.bontech.intershipt.demo.repositories;

import com.bontech.intershipt.demo.models.db.ServiceActivationDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceActivationDateRepository extends JpaRepository<ServiceActivationDate,Long> {

    Optional<ServiceActivationDate> findByServiceIdAndDateAndStartTimeAndEndTime(Long ServiceId, LocalDate date, Float startTime , Float endTime);

    @Query(nativeQuery = true , value = "select service_id from service_activation_date where date = ?1 and start_time <= ?2 and end_time >= ?2 and is_active = true")
    List<Long> findAllIdByDateAndTimeAndIsActiveTrue(LocalDate date , float hour);

    boolean existsByDate(LocalDate date);

    @Query(nativeQuery = true, value = "update service_activation_date as sad set sad.is_active = ?1 where sad.service_id = ?2")
    @Transactional
    @Modifying
    void updateIsActive(boolean isActive,Long serviceId);

    boolean existsByIsActiveFalse();

}
