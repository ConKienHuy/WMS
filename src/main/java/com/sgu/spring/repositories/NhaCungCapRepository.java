package com.sgu.spring.repositories;

import com.sgu.spring.models.NhaCungCap;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, Long> {

    @Query("select e from NhaCungCap e where e.isEnable = true")
    List<NhaCungCap> findAllActive();

    boolean existsByMaNCC(String maNCC);

    boolean existsByTenNCC(String tenNCC);
}
