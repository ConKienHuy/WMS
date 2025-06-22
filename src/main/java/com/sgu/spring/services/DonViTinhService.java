package com.sgu.spring.services;

import com.sgu.spring.dtos.donvitinhs.DonViTinhRequest;
import com.sgu.spring.dtos.donvitinhs.DonViTinhResponse;

import java.util.List;

public interface DonViTinhService {
    List<DonViTinhResponse> getAllActive();
    DonViTinhResponse getById(Long id);
    DonViTinhResponse create(DonViTinhRequest donViTinhRequest);
    DonViTinhResponse update(Long id, DonViTinhRequest donViTinhRequest);
    DonViTinhResponse delete(Long id);
}
