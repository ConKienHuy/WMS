package com.sgu.spring.services;

import com.sgu.spring.dtos.khos.KhoRequest;
import com.sgu.spring.dtos.khos.KhoResponse;

import java.util.List;

public interface KhoService {
    List<KhoResponse> getAllActive();
    KhoResponse getById(Long id);
    KhoResponse create(KhoRequest khoRequest);
    KhoResponse update(Long id, KhoRequest khoRequest);
    KhoResponse delete(Long id);
}
