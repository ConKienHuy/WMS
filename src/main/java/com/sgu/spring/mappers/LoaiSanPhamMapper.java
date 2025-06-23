package com.sgu.spring.mappers;

import com.sgu.spring.dtos.loaisanphams.LoaiSanPhamRequest;
import com.sgu.spring.dtos.loaisanphams.LoaiSanPhamResponse;
import com.sgu.spring.models.LoaiSanPham;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoaiSanPhamMapper {

    LoaiSanPham toEntity(LoaiSanPhamRequest addLSPRequest);

    LoaiSanPhamResponse toDto(LoaiSanPham lsp);

    List<LoaiSanPhamResponse> toDtoList(List<LoaiSanPham> lspList);

    LoaiSanPham updateEntity(@MappingTarget LoaiSanPham loaiSanPham, LoaiSanPhamRequest updateLSPRequest);
}
