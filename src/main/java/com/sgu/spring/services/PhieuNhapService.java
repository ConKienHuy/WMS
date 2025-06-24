package com.sgu.spring.services;

import com.sgu.spring.dtos.phieunhaps.AddPhieuNhapRequest;
import com.sgu.spring.dtos.phieunhaps.PhieuNhapResponse;
import com.sgu.spring.dtos.phieunhaps.UpdatePhieuNhapRequest;

public interface PhieuNhapService {
    PhieuNhapResponse getById(Long id);
    PhieuNhapResponse create(AddPhieuNhapRequest addRequest);
    PhieuNhapResponse update(Long id, UpdatePhieuNhapRequest updateRequest);
    PhieuNhapResponse delete(Long id);
}
