package com.sgu.spring.services;

import com.sgu.spring.dtos.loaisanphams.LoaiSanPhamRequest;
import com.sgu.spring.dtos.loaisanphams.LoaiSanPhamResponse;

import java.util.List;

public interface LoaiSanPhamService {
    List<LoaiSanPhamResponse> getAllActive();
    LoaiSanPhamResponse getById(Long id);
    LoaiSanPhamResponse create(LoaiSanPhamRequest loaiSanPhamRequest);
    LoaiSanPhamResponse update(Long id, LoaiSanPhamRequest loaiSanPhamRequest);
    LoaiSanPhamResponse delete(Long id);
}
