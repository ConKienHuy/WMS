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

    SanPham toEntity(SanPhamRequest addSanPhamRequest);

    SanPhamResponse toDto(SanPham sanPham);

    List<SanPhamResponse> toDtoList(List<SanPham> sanPhamList);

    SanPham updateEntity(@MappingTarget SanPham sanPham, SanPhamRequest updateSanPhamRequest);
}