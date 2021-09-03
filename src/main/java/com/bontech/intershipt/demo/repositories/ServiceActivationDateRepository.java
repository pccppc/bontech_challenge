package com.bontech.intershipt.demo.repositories;

import com.bontech.intershipt.demo.models.ServiceActivationDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceActivationDateRepository extends JpaRepository<ServiceActivationDate,Long> {
}
