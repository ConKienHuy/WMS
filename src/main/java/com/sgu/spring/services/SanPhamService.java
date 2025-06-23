package com.sgu.spring.services;

import com.sgu.spring.dtos.sanphams.SanPhamRequest;
import com.sgu.spring.dtos.sanphams.SanPhamResponse;

import java.util.List;

public interface SanPhamService {
    List<SanPhamResponse> getAllActive();

    SanPhamResponse getById(Long id);

    SanPhamResponse create(SanPhamRequest sanPhamRequest);

    SanPhamResponse update(Long id, SanPhamRequest sanPhamRequest);

    SanPhamResponse delete(Long id);
}
