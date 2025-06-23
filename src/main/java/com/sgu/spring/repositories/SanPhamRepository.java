package com.sgu.spring.repositories;

import com.sgu.spring.models.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    @Query("select e from SanPham e where e.isEnable = true")
    List<SanPham> findAllActive();

    SanPham findByMaSanPham(String maSP);

    SanPham findByTenSanPham(String tenSP);
}
