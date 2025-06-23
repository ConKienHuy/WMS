package com.sgu.spring.services.impl;

import com.sgu.spring.dtos.donvitinhs.DonViTinhRequest;
import com.sgu.spring.dtos.donvitinhs.DonViTinhResponse;
import com.sgu.spring.exceptions.EntityInactiveException;
import com.sgu.spring.exceptions.EntityNotFoundException;
import com.sgu.spring.mappers.DonViTinhMapper;
import com.sgu.spring.models.DonViTinh;
import com.sgu.spring.repositories.DonViTinhRepository;
import com.sgu.spring.services.DonViTinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonViTinhServiceImpl implements DonViTinhService {
    private DonViTinhRepository donViTinhRepository;
    private DonViTinhMapper donViTinhMapper;

    @Autowired
    public DonViTinhServiceImpl(DonViTinhRepository donViTinhRepository, DonViTinhMapper donViTinhMapper) {
        this.donViTinhRepository = donViTinhRepository;
        this.donViTinhMapper = donViTinhMapper;
    }

    @Override
    public List<DonViTinhResponse> getAllActive() {
        List<DonViTinh> donViTinhList = donViTinhRepository.findAllActive();
        return donViTinhMapper.toDtoList(donViTinhList);
    }

    @Override
    public DonViTinhResponse getById(Long id) {
        DonViTinh donViTinh = this.getEntityById(id);
        return donViTinhMapper.toDto(donViTinh);
    }

    @Override
    public DonViTinhResponse create(DonViTinhRequest addRequest) {
        DonViTinh donViTinh = donViTinhMapper.toEntity(addRequest);

        donViTinh.setEnable(true);
        donViTinh.setNgayTao(LocalDateTime.now());
        donViTinh.setNgayCapNhat(LocalDateTime.now());
        donViTinhRepository.save(donViTinh);

        return donViTinhMapper.toDto(donViTinh);
    }

    @Override
    public DonViTinhResponse update(Long id, DonViTinhRequest updateRequest) {
        DonViTinh donViTinh = this.getEntityById(id);

        donViTinhMapper.updateEntity(donViTinh, updateRequest);
        donViTinh.setNgayCapNhat(LocalDateTime.now());
        donViTinhRepository.save(donViTinh);

        return donViTinhMapper.toDto(donViTinh);
    }

    @Override
    public DonViTinhResponse delete(Long id) {
        DonViTinh donViTinh = this.getEntityById(id);

        donViTinh.setEnable(false);
        donViTinh.setNgayCapNhat(LocalDateTime.now());
        donViTinhRepository.save(donViTinh);
        return donViTinhMapper.toDto(donViTinh);
    }

    private DonViTinh getEntityById(Long id) {
        DonViTinh donViTinh = donViTinhRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy đơn vị tính với ID: " + id + "."));
        if(!donViTinh.isEnable()) {
            throw new EntityInactiveException("Đơn vị tính với ID: " + id + " không hoạt động.");
        }

        return donViTinh;
    }

}
