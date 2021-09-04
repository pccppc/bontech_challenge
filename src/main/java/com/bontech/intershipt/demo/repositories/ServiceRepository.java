package com.bontech.intershipt.demo.repositories;

import com.bontech.intershipt.demo.models.db.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {

    @Transactional
    @Modifying
    @Query(value = "update Service s set s.name = ?1 ,s.fee = ?2 where s.id = ?3")
    void updateServiceById(String name,Long fee,Long id);

    boolean existsById(Long id);

}
