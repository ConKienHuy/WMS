package com.sgu.spring.services.impl;

import com.sgu.spring.dtos.nhacungcaps.NhaCungCapRequest;
import com.sgu.spring.dtos.nhacungcaps.NhaCungCapResponse;
import com.sgu.spring.exceptions.DuplicateResourceException;
import com.sgu.spring.exceptions.EntityInactiveException;
import com.sgu.spring.exceptions.EntityNotFoundException;
import com.sgu.spring.mappers.NhaCungCapMapper;
import com.sgu.spring.models.NhaCungCap;
import com.sgu.spring.repositories.NhaCungCapRepository;
import com.sgu.spring.services.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NhaCungCapServiceImpl implements NhaCungCapService {
    private NhaCungCapRepository nhaCungCapRepository;
    private NhaCungCapMapper nhaCungCapMapper;

    @Autowired
    public NhaCungCapServiceImpl(NhaCungCapRepository nhaCungCapRepository, NhaCungCapMapper nhaCungCapMapper) {
        this.nhaCungCapRepository = nhaCungCapRepository;
        this.nhaCungCapMapper = nhaCungCapMapper;
    }

    @Override
    public List<NhaCungCapResponse> getAllActive() {
        List<NhaCungCap> nhaCungCapList = nhaCungCapRepository.findAllActive();

        return nhaCungCapList.stream().map(nhaCungCapMapper::toDto).toList();
    }

    @Override
    public NhaCungCapResponse getById(Long id) {
        NhaCungCap nhaCungCap = this.getEntityById(id);
        return nhaCungCapMapper.toDto(nhaCungCap);
    }

    @Override
    public NhaCungCapResponse create(NhaCungCapRequest addRequest) {
        if (nhaCungCapRepository.existsByMaNCC(addRequest.getMaNCC())) {
            throw new DuplicateResourceException("Mã nhà cung cấp đã tồn tại.");
        }
        if (nhaCungCapRepository.existsByTenNCC(addRequest.getTenNCC())) {
           throw new DuplicateResourceException("Tên nhà cung cấp đã tồn tại.");
        }
        NhaCungCap nhaCungCap = nhaCungCapMapper.toEntity(addRequest);

        nhaCungCap.setEnable(true);
        nhaCungCap.setNgayTao(LocalDateTime.now());
        nhaCungCap.setNgayCapNhat(LocalDateTime.now());
        nhaCungCapRepository.save(nhaCungCap);

        return nhaCungCapMapper.toDto(nhaCungCap);
    }

    @Override
    public NhaCungCapResponse update(Long id, NhaCungCapRequest updateRequest) {
        NhaCungCap nhaCungCap = this.getEntityById(id);
        if (nhaCungCapRepository.existsByMaNCC(updateRequest.getMaNCC()) && nhaCungCap.getTenNCC().equals(updateRequest.getMaNCC())) {
            throw new DuplicateResourceException("Mã nhà cung cấp đã tồn tại.");
        }
        if (nhaCungCapRepository.existsByTenNCC(updateRequest.getTenNCC()) && nhaCungCap.getTenNCC().equals(updateRequest.getTenNCC())) {
            throw new DuplicateResourceException("Tên nhà cung cấp đã tồn tại.");
        }

        nhaCungCapMapper.updateEntity(nhaCungCap, updateRequest);
        nhaCungCap.setNgayCapNhat(LocalDateTime.now());
        nhaCungCapRepository.save(nhaCungCap);

        return nhaCungCapMapper.toDto(nhaCungCap);
    }

    @Override
    public NhaCungCapResponse delete(Long id) {
        NhaCungCap nhaCungCap = this.getEntityById(id);

        nhaCungCap.setEnable(false);
        nhaCungCap.setNgayCapNhat(LocalDateTime.now());
        nhaCungCapRepository.save(nhaCungCap);
        return nhaCungCapMapper.toDto(nhaCungCap);
    }

    private NhaCungCap getEntityById(Long id) {
        NhaCungCap nhaCungCap = nhaCungCapRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy nhà cung cấp với ID: " + id + "."));
        if (!nhaCungCap.isEnable()) {
            throw new EntityInactiveException("Nhà cung cấp với ID: " + id + " không hoạt động.");
        }

        return nhaCungCap;
    }

}
