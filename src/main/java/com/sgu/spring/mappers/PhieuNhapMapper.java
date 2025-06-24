package com.sgu.spring.mappers;

import com.sgu.spring.dtos.phieunhaps.AddPhieuNhapRequest;
import com.sgu.spring.dtos.phieunhaps.PhieuNhapResponse;
import com.sgu.spring.dtos.phieunhaps.UpdatePhieuNhapRequest;
import com.sgu.spring.models.PhieuNhap;
import com.sgu.spring.repositories.KhoRepository;
import com.sgu.spring.repositories.NhaCungCapRepository;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PhieuNhapMapper {

    @Mapping(target = "ngayTao", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "ngayCapNhat", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "enable", constant = "true")
    PhieuNhap toEntity(AddPhieuNhapRequest addRequest, @Context KhoRepository khoRepo, @Context NhaCungCapRepository nccRepo);

    @Mapping(target = "ngayCapNhat", expression = "java(java.time.LocalDateTime.now())")
    void updateEntity(@MappingTarget PhieuNhap phieuNhap, UpdatePhieuNhapRequest updateRequest, @Context KhoRepository khoRepo, @Context NhaCungCapRepository nccRepo);

    PhieuNhapResponse toDTO(PhieuNhap phieuNhap);

    @AfterMapping
    default void mapRelationship(@MappingTarget PhieuNhap phieuNhap,
                                  AddPhieuNhapRequest request,
                                  @Context KhoRepository khoRepo,
                                  @Context NhaCungCapRepository nccRepo) {
        phieuNhap.setKho(khoRepo.findById(request.getKhoID()).get());
        phieuNhap.setNhaCungCap(nccRepo.findById(request.getNccID()).get());
    }
}
