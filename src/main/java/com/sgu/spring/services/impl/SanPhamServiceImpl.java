package com.sgu.spring.services.impl;

import com.sgu.spring.dtos.sanphams.SanPhamRequest;
import com.sgu.spring.dtos.sanphams.SanPhamResponse;
import com.sgu.spring.exceptions.DuplicateResourceException;
import com.sgu.spring.exceptions.EntityInactiveException;
import com.sgu.spring.exceptions.EntityNotFoundException;
import com.sgu.spring.mappers.DonViTinhMapper;
import com.sgu.spring.mappers.LoaiSanPhamMapper;
import com.sgu.spring.mappers.SanPhamMapper;
import com.sgu.spring.models.DonViTinh;
import com.sgu.spring.models.LoaiSanPham;
import com.sgu.spring.models.SanPham;
import com.sgu.spring.repositories.DonViTinhRepository;
import com.sgu.spring.repositories.LoaiSanPhamRepository;
import com.sgu.spring.repositories.SanPhamRepository;
import com.sgu.spring.services.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    private SanPhamRepository sanPhamRepository;
    private SanPhamMapper sanPhamMapper;

    private LoaiSanPhamRepository loaiSanPhamRepository;
    private DonViTinhRepository donViTinhRepository;

    @Autowired
    public SanPhamServiceImpl(SanPhamRepository sanPhamRepository, SanPhamMapper sanPhamMapper, LoaiSanPhamRepository loaiSanPhamRepository, DonViTinhRepository donViTinhRepository) {
        this.sanPhamRepository = sanPhamRepository;
        this.sanPhamMapper = sanPhamMapper;
        this.loaiSanPhamRepository = loaiSanPhamRepository;
        this.donViTinhRepository = donViTinhRepository;
    }

    @Override
    public List<SanPhamResponse> getAllActive() {
        List<SanPham> sanPhamList = sanPhamRepository.findAllActive();

        return sanPhamList.stream().map(sanPhamMapper::toDto).toList();
    }

    @Override
    public SanPhamResponse getById(Long id) {
        SanPham sanPham = this.getEntityById(id);
        return sanPhamMapper.toDto(sanPham);
    }

    @Override
    public SanPhamResponse create(SanPhamRequest sanPhamRequest) {
        // Check trùng tên
        if(sanPhamRepository.findByMaSanPham(sanPhamRequest.getMaSanPham()) != null) {
            throw new DuplicateResourceException("Mã sản phẩm: " +sanPhamRequest.getMaSanPham()+ " đã tồn tại.");
        }
        if(sanPhamRepository.findByTenSanPham(sanPhamRequest.getTenSanPham()) != null) {
            throw new DuplicateResourceException("Tên sản phẩm: " +sanPhamRequest.getTenSanPham()+ " đã tồn tại.");
        }

        SanPham sanPham = sanPhamMapper.toEntity(sanPhamRequest);
        sanPham.setLoaiSanPham(getLoaiSanPhamById(sanPhamRequest.getLoaiSanPhamID()));
        sanPham.setDonViTinh(getDonViTinhById(sanPhamRequest.getDonViTinhID()));
        sanPham.setEnable(true);
        sanPham.setNgayTao(LocalDateTime.now());
        sanPham.setNgayCapNhat(LocalDateTime.now());
        sanPhamRepository.save(sanPham);
        return sanPhamMapper.toDto(sanPham);
    }

    @Override
    public SanPhamResponse update(Long id, SanPhamRequest sanPhamRequest) {
        SanPham sanPham = this.getEntityById(id);
        if(sanPhamRepository.findByMaSanPham(sanPhamRequest.getMaSanPham()) != null && !sanPham.getMaSanPham().equals(sanPhamRequest.getMaSanPham())) {
            throw new DuplicateResourceException("Mã sản phẩm: " +sanPhamRequest.getMaSanPham()+ " đã tồn tại.");
        }
        if(sanPhamRepository.findByTenSanPham(sanPhamRequest.getTenSanPham()) != null  && !sanPham.getMaSanPham().equals(sanPhamRequest.getMaSanPham())) {
            throw new DuplicateResourceException("Tên sản phẩm: " +sanPhamRequest.getTenSanPham()+ " đã tồn tại.");
        }

        sanPhamMapper.updateEntity(sanPham, sanPhamRequest);
        sanPham.setLoaiSanPham(getLoaiSanPhamById(sanPhamRequest.getLoaiSanPhamID()));
        sanPham.setDonViTinh(getDonViTinhById(sanPhamRequest.getDonViTinhID()));
        sanPham.setNgayCapNhat(LocalDateTime.now());

        sanPhamRepository.save(sanPham);
        return sanPhamMapper.toDto(sanPham);
    }

    @Override
    public SanPhamResponse delete(Long id) {
        SanPham sanPham = this.getEntityById(id);

        sanPham.setEnable(false);
        sanPham.setNgayCapNhat(LocalDateTime.now());
        sanPhamRepository.save(sanPham);

        return sanPhamMapper.toDto(sanPham);
    }

    private SanPham getEntityById(Long id) {
        SanPham sanPham = sanPhamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sản phẩm với ID: " + id));
        if (!sanPham.isEnable()) {
            throw new EntityInactiveException("Sản phẩm với ID: " + id + " không hoạt động.");
        }
        return sanPham;
    }

    private LoaiSanPham getLoaiSanPhamById(Long id) {
        LoaiSanPham loaiSanPham = loaiSanPhamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy loại sản phẩm với ID: " + id));
        if (!loaiSanPham.isEnable()) {
            throw new EntityInactiveException("Loại sản phẩm với ID: " + id + " không hoạt động.");
        }
        return loaiSanPham;
    }

    private DonViTinh getDonViTinhById(Long id) {
        DonViTinh donViTinh = donViTinhRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy đơn vị tính với ID: " + id));
        if (!donViTinh.isEnable()) {
            throw new EntityInactiveException("Đơn vị tính với ID: " + id + " không hoạt động.");
        }
        return donViTinh;
    }
}
