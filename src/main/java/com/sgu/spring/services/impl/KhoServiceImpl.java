package com.sgu.spring.services.impl;

import com.sgu.spring.dtos.khos.KhoRequest;
import com.sgu.spring.dtos.khos.KhoResponse;
import com.sgu.spring.exceptions.DuplicateResourceException;
import com.sgu.spring.exceptions.EntityInactiveException;
import com.sgu.spring.exceptions.EntityNotFoundException;
import com.sgu.spring.mappers.KhoMapper;
import com.sgu.spring.models.Kho;
import com.sgu.spring.repositories.KhoRepository;
import com.sgu.spring.services.KhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KhoServiceImpl implements KhoService {
    private KhoRepository khoRepository;
    private KhoMapper khoMapper;

    @Autowired
    public KhoServiceImpl(KhoRepository khoRepository, KhoMapper khoMapper) {
        this.khoRepository = khoRepository;
        this.khoMapper = khoMapper;
    }

    @Override
    public List<KhoResponse> getAllActive() {
        List<Kho> khoList = khoRepository.findAllActive();
        return khoMapper.toDtoList(khoList);
    }

    @Override
    public KhoResponse getById(Long id) {
        Kho kho = this.getEntityById(id);
        return khoMapper.toDto(kho);
    }

    @Override
    public KhoResponse create(KhoRequest addRequest) {
        if (khoRepository.existsByTenKho(addRequest.getTenKho())) {
            throw new DuplicateResourceException("Tên kho đã tồn tại.");
        }
        Kho kho = khoMapper.toEntity(addRequest);

        kho.setEnable(true);
        kho.setNgayTao(LocalDateTime.now());
        kho.setNgayCapNhat(LocalDateTime.now());
        khoRepository.save(kho);

        return khoMapper.toDto(kho);
    }

    @Override
    public KhoResponse update(Long id, KhoRequest updateRequest) {
        Kho kho = this.getEntityById(id);
        if (khoRepository.existsByTenKho(updateRequest.getTenKho()) && !kho.getTenKho().equals(updateRequest.getTenKho())) {
            throw new DuplicateResourceException("Tên kho đã tồn tại.");
        }

        khoMapper.updateEntity(kho, updateRequest);
        kho.setNgayCapNhat(LocalDateTime.now());
        khoRepository.save(kho);

        return khoMapper.toDto(kho);
    }

    @Override
    public KhoResponse delete(Long id) {
        Kho kho = this.getEntityById(id);

        kho.setEnable(false);
        kho.setNgayCapNhat(LocalDateTime.now());
        khoRepository.save(kho);
        return khoMapper.toDto(kho);
    }

    private Kho getEntityById(Long id) {
        Kho kho = khoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy kho với ID: " + id + "."));
        if (!kho.isEnable()) {
            throw new EntityInactiveException("Kho với ID: " + id + " không hoạt động.");
        }

        return kho;
    }
}
