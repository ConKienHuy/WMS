package com.sgu.spring.services;

import com.sgu.spring.dtos.nhacungcaps.NhaCungCapRequest;
import com.sgu.spring.dtos.nhacungcaps.NhaCungCapResponse;

import java.util.List;

public interface NhaCungCapService {
    List<NhaCungCapResponse> getAllActive();
    NhaCungCapResponse getById(Long id);
    NhaCungCapResponse create(NhaCungCapRequest nhaCungCapRequest);
    NhaCungCapResponse update(Long id, NhaCungCapRequest nhaCungCapRequest);
    NhaCungCapResponse delete(Long id);
}
