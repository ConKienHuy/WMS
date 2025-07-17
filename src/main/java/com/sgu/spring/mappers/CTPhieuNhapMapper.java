package com.sgu.spring.mappers;

import com.sgu.spring.dtos.phieunhaps.CTPNRequest;
import com.sgu.spring.dtos.phieunhaps.CTPNResponse;
import com.sgu.spring.models.ChiTietPhieuNhap;
import com.sgu.spring.repositories.SanPhamRepository;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CTPhieuNhapMapper {

    @Mapping(source = "soLuongNhap", target = "soLuongNhap")
    @Mapping(source = "donGiaNhapNhap", target = "donGiaNhapNhap")
    ChiTietPhieuNhap toEntity(CTPNRequest addRequest);

    @Mapping(source = "soLuongNhap", target = "soLuongNhap")
    @Mapping(source = "donGiaNhapNhap", target = "donGiaNhapNhap")
    void updateEntity(@MappingTarget ChiTietPhieuNhap ctPhieuNhap , CTPNRequest request);



}
