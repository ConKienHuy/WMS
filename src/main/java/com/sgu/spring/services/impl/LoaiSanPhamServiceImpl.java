package com.sgu.spring.services.impl;

import com.sgu.spring.dtos.loaisanphams.LoaiSanPhamRequest;
import com.sgu.spring.dtos.loaisanphams.LoaiSanPhamResponse;
import com.sgu.spring.exceptions.EntityInactiveException;
import com.sgu.spring.exceptions.EntityNotFoundException;
import com.sgu.spring.mappers.LoaiSanPhamMapper;
import com.sgu.spring.models.LoaiSanPham;
import com.sgu.spring.repositories.LoaiSanPhamRepository;
import com.sgu.spring.services.LoaiSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoaiSanPhamServiceImpl implements LoaiSanPhamService {
    private LoaiSanPhamRepository loaiSanPhamRepository;
    private LoaiSanPhamMapper loaiSanPhamMapper;

    @Autowired
    public LoaiSanPhamServiceImpl(LoaiSanPhamRepository loaiSanPhamRepository, LoaiSanPhamMapper loaiSanPhamMapper) {
        this.loaiSanPhamRepository = loaiSanPhamRepository;
        this.loaiSanPhamMapper = loaiSanPhamMapper;
    }

    @Override
    public List<LoaiSanPhamResponse> getAllActive() {
        List<LoaiSanPham> loaiSanPhamList = loaiSanPhamRepository.findAllActive();

        return loaiSanPhamList.stream().map(loaiSanPhamMapper::toDto).toList();
    }

    @Override
    public LoaiSanPhamResponse getById(Long id) {
        LoaiSanPham loaiSanPham = this.getEntityById(id);
        return loaiSanPhamMapper.toDto(loaiSanPham);
    }

    @Override
    public LoaiSanPhamResponse create(LoaiSanPhamRequest addRequest) {
        LoaiSanPham loaiSanPham = loaiSanPhamMapper.toEntity(addRequest);

        loaiSanPham.setEnable(true);
        loaiSanPham.setNgayTao(LocalDateTime.now());
        loaiSanPham.setNgayCapNhat(LocalDateTime.now());
        loaiSanPhamRepository.save(loaiSanPham);

        return loaiSanPhamMapper.toDto(loaiSanPham);
    }

    @Override
    public LoaiSanPhamResponse update(Long id, LoaiSanPhamRequest updateRequest) {
        LoaiSanPham loaiSanPham = this.getEntityById(id);

        loaiSanPhamMapper.updateEntity(loaiSanPham, updateRequest);
        loaiSanPham.setNgayCapNhat(LocalDateTime.now());
        loaiSanPhamRepository.save(loaiSanPham);

        return loaiSanPhamMapper.toDto(loaiSanPham);
    }

    @Override
    public LoaiSanPhamResponse delete(Long id) {
        LoaiSanPham loaiSanPham = this.getEntityById(id);

        loaiSanPham.setEnable(false);
        loaiSanPham.setNgayCapNhat(LocalDateTime.now());
        loaiSanPhamRepository.save(loaiSanPham);
        return loaiSanPhamMapper.toDto(loaiSanPham);
    }

    private LoaiSanPham getEntityById(Long id) {
        LoaiSanPham loaiSanPham = loaiSanPhamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy loại sản phẩm với ID: " + id + "."));
        if (!loaiSanPham.isEnable()) {
            throw new EntityInactiveException("Loại sản phẩm với ID: " + id + " không hoạt động.");
        }

        return loaiSanPham;
    }

}