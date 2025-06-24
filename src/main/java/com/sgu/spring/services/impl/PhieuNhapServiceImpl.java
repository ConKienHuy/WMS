package com.sgu.spring.services.impl;

import com.sgu.spring.dtos.phieunhaps.AddPhieuNhapRequest;
import com.sgu.spring.dtos.phieunhaps.CTPNRequest;
import com.sgu.spring.dtos.phieunhaps.PhieuNhapResponse;
import com.sgu.spring.dtos.phieunhaps.UpdatePhieuNhapRequest;
import com.sgu.spring.exceptions.EntityInactiveException;
import com.sgu.spring.exceptions.EntityNotFoundException;
import com.sgu.spring.mappers.CTPhieuNhapMapper;
import com.sgu.spring.mappers.PhieuNhapMapper;
import com.sgu.spring.models.*;
import com.sgu.spring.repositories.*;
import com.sgu.spring.services.PhieuNhapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhieuNhapServiceImpl implements PhieuNhapService {
    private PhieuNhapRepository phieuNhapRepo;
    private KhoRepository khoRepo;
    private NhaCungCapRepository nccRepo;
    private ChiTietPhieuNhapRepository ctRepo;
    private SanPhamRepository sanPhamRepo;
    private PhieuNhapMapper phieuNhapMapper;
    private CTPhieuNhapMapper ctMapper;

    @Autowired
    public PhieuNhapServiceImpl(PhieuNhapRepository phieuNhapRepository, KhoRepository khoRepo, NhaCungCapRepository nccRepo, ChiTietPhieuNhapRepository ctRepo, SanPhamRepository sanPhamRepo, PhieuNhapMapper phieuNhapMapper, CTPhieuNhapMapper ctMapper) {
        this.phieuNhapRepo = phieuNhapRepository;
        this.khoRepo = khoRepo;
        this.nccRepo = nccRepo;
        this.ctRepo = ctRepo;
        this.sanPhamRepo = sanPhamRepo;
        this.phieuNhapMapper = phieuNhapMapper;
        this.ctMapper = ctMapper;
    }

    @Override
    public PhieuNhapResponse getById(Long id) {
        PhieuNhap phieuNhap = phieuNhapRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Không tìm thấy phiếu nhập với ID: " +id+ " trên hệ thống."));
        if(!phieuNhap.isEnable()) {
            throw new EntityInactiveException("Phiếu nhập không hoạt động.");
        }
        PhieuNhapResponse response = phieuNhapMapper.toDTO(phieuNhap);
        return response;
    }

    @Override
    public PhieuNhapResponse create(AddPhieuNhapRequest addRequest) {
        PhieuNhap phieuNhap = phieuNhapMapper.toEntity(addRequest, khoRepo, nccRepo);
        phieuNhapRepo.save(phieuNhap);

        List<ChiTietPhieuNhap> chiTietList = new ArrayList<>();
        for (CTPNRequest addCTRequest : addRequest.getChiTietPhieuNhap()) {
            ChiTietPhieuNhap chiTietPhieuNhap = ctMapper.toEntity(addCTRequest, sanPhamRepo);
            chiTietPhieuNhap.setPhieuNhap(phieuNhap);
            chiTietList.add(chiTietPhieuNhap);
        }
        ctRepo.saveAll(chiTietList);

        PhieuNhapResponse response = phieuNhapMapper.toDTO(phieuNhap);

        return response;
    }

    // Sửa header phiếu nhập
    @Override
    public PhieuNhapResponse update(Long id, UpdatePhieuNhapRequest updateRequest) {
        PhieuNhap phieuNhap = phieuNhapRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Không tìm thấy phiếu nhập với ID: " +id+ " trên hệ thống."));
        if(!phieuNhap.isEnable()) {
            throw new EntityInactiveException("Phiếu nhập không hoạt động.");
        }
        phieuNhapMapper.updateEntity(phieuNhap ,updateRequest, khoRepo, nccRepo);
        phieuNhapRepo.save(phieuNhap);
        PhieuNhapResponse response = phieuNhapMapper.toDTO(phieuNhap);
        return response;
    }

    @Override
    public PhieuNhapResponse delete(Long id) {
        PhieuNhap phieuNhap = phieuNhapRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Không tìm thấy phiếu nhập với ID: " +id+ " trên hệ thống."));
        if(!phieuNhap.isEnable()) {
            throw new EntityInactiveException("Phiếu nhập không hoạt động.");
        }
        phieuNhap.setEnable(false);
        phieuNhap.setNgayCapNhat(LocalDateTime.now());
        phieuNhapRepo.save(phieuNhap);
        return phieuNhapMapper.toDTO(phieuNhap);
    }
}
