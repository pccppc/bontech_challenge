package com.bontech.intershipt.demo.repositories;

import com.bontech.intershipt.demo.models.ServiceUsesHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceUsesHistoryRepository extends JpaRepository<ServiceUsesHistory,ServiceUsesHistory> {
    List<ServiceUsesHistory> findAllByUserId(Long userId);
}
