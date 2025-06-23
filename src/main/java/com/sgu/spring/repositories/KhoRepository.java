package com.sgu.spring.repositories;

import com.sgu.spring.models.Kho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KhoRepository extends JpaRepository<Kho, Long> {

    @Query("select e from Kho e where e.isEnable = true")
    List<Kho> findAllActive();

    boolean existsByTenKho(String tenKho);
}
