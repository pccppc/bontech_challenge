package com.bontech.intershipt.demo.repositories;

import com.bontech.intershipt.demo.models.db.ServiceActivationDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceActivationDateRepository extends JpaRepository<ServiceActivationDate,Long> {

    Optional<ServiceActivationDate> findByServiceIdAndDateAndStartTimeAndEndTime(Long ServiceId, LocalDate date, Float startTime , Float endTime);

    @Query(nativeQuery = true , value = "select service_id from ServiceActivationDate where date = ?1 and startTime <= ?2 and endTime >= ?2 and isActive = true")
    List<Long> findAllIdByDateAndTimeAndIsActiveTrue(LocalDate date , float hour);
}
