package com.sgu.spring.repositories;

import com.sgu.spring.models.LoaiSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPham, Long> {
    @Query("select e from LoaiSanPham e where e.isEnable = true")
    List<LoaiSanPham> findAllActive();
}
