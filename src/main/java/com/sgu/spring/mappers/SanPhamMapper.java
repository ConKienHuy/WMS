package com.sgu.spring.mappers;

import com.sgu.spring.dtos.sanphams.SanPhamRequest;
import com.sgu.spring.dtos.sanphams.SanPhamResponse;
import com.sgu.spring.models.SanPham;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {LoaiSanPhamMapper.class, DonViTinhMapper.class})
public interface SanPhamMapper {

    @Mapping(source = "maSanPham", target = "maSanPham")
    @Mapping(source = "tenSanPham", target = "tenSanPham")
    SanPham toEntity(SanPhamRequest addSanPhamRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "maSanPham", target = "maSanPham")
    @Mapping(source = "tenSanPham", target = "tenSanPham")
    SanPhamResponse toDto(SanPham sanPham);

    @Mapping(source = "maSanPham", target = "maSanPham")
    @Mapping(source = "tenSanPham", target = "tenSanPham")
    SanPham updateEntity(@MappingTarget SanPham sanPham, SanPhamRequest updateSanPhamRequest);
}