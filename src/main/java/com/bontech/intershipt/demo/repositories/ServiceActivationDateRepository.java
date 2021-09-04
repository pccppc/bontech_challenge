package com.bontech.intershipt.demo.repositories;

import com.bontech.intershipt.demo.models.ServiceActivationDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ServiceActivationDateRepository extends JpaRepository<ServiceActivationDate,Long> {

    Optional<ServiceActivationDate> findByServiceIdAndDateAndStartTimeAndEndTime(Long ServiceId, LocalDate date, Float startTime , Float endTime);
}
