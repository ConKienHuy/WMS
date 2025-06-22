package com.sgu.spring.repositories;

import com.sgu.spring.models.DonViTinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonViTinhRepository extends JpaRepository<DonViTinh, Long> {

    @Query("select e from DonViTinh e where e.isEnable = true")
    List<DonViTinh> findAllActive();
}
