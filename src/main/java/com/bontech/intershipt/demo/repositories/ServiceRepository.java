package com.bontech.intershipt.demo.repositories;

import com.bontech.intershipt.demo.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
}
