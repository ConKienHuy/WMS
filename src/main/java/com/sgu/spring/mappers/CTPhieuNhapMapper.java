package com.sgu.spring.mappers;

import com.sgu.spring.dtos.phieunhaps.CTPNRequest;
import com.sgu.spring.dtos.phieunhaps.CTPNResponse;
import com.sgu.spring.models.ChiTietPhieuNhap;
import com.sgu.spring.repositories.SanPhamRepository;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CTPhieuNhapMapper {

    @Mapping(target = "ngayTao", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "ngayCapNhat", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "enable", constant = "true")
    ChiTietPhieuNhap toEntity(CTPNRequest addRequest, @Context SanPhamRepository spRepo);

    @Mapping(target = "ngayCapNhat", expression = "java(java.time.LocalDateTime.now())")
    void updateEntity(@MappingTarget ChiTietPhieuNhap ctPhieuNhap , CTPNRequest request, @Context SanPhamRepository spRepo);

    @Mapping(target = "enable", constant = "true")
    List<CTPNResponse> toCTPNResponseList(List<ChiTietPhieuNhap> ctList, @Context SanPhamRepository spRepo);

    @AfterMapping
    default void mapRelationship(@MappingTarget ChiTietPhieuNhap chiTietPhieuNhap,
                                 CTPNRequest request,
                                 @Context SanPhamRepository spRepo) {
        chiTietPhieuNhap.setSanPham(spRepo.findById(request.getSanPhamID()).get());
    }

}
