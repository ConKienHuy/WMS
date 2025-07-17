package com.sgu.spring.mappers;

import com.sgu.spring.dtos.loaisanphams.LoaiSanPhamRequest;
import com.sgu.spring.dtos.loaisanphams.LoaiSanPhamResponse;
import com.sgu.spring.models.LoaiSanPham;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LoaiSanPhamMapper {

    @Mapping(source = "maLSP", target = "maLSP")
    @Mapping(source = "tenLSP", target = "tenLSP")
    @Mapping(source = "ghiChu", target = "ghiChu")
    LoaiSanPham toEntity(LoaiSanPhamRequest addLSPRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "tenLSP", target = "tenLSP")
    @Mapping(source = "ghiChu", target = "ghiChu")
    LoaiSanPhamResponse toDto(LoaiSanPham lsp);

    @Mapping(source = "maLSP", target = "maLSP")
    @Mapping(source = "tenLSP", target = "tenLSP")
    @Mapping(source = "ghiChu", target = "ghiChu")
    LoaiSanPham updateEntity(@MappingTarget LoaiSanPham loaiSanPham, LoaiSanPhamRequest updateLSPRequest);
}
