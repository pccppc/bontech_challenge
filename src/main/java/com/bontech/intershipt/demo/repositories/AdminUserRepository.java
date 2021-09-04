package com.bontech.intershipt.demo.repositories;

import com.bontech.intershipt.demo.models.db.usr.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser,Long> {
}
