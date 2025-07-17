package com.sgu.spring.mappers;

import com.sgu.spring.dtos.nhacungcaps.NhaCungCapRequest;
import com.sgu.spring.dtos.nhacungcaps.NhaCungCapResponse;
import com.sgu.spring.models.NhaCungCap;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NhaCungCapMapper {

    @Mapping(source = "maNCC", target = "maNCC")
    @Mapping(source = "tenNCC", target = "tenNCC")
    @Mapping(source = "ghiChu", target = "ghiChu")
    NhaCungCap toEntity(NhaCungCapRequest addNhaCungCapRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "maNCC", target = "maNCC")
    @Mapping(source = "tenNCC", target = "tenNCC")
    @Mapping(source = "ghiChu", target = "ghiChu")
    NhaCungCapResponse toDto(NhaCungCap nhaCungCap);

    @Mapping(source = "maNCC", target = "maNCC")
    @Mapping(source = "tenNCC", target = "tenNCC")
    @Mapping(source = "ghiChu", target = "ghiChu")
    NhaCungCap updateEntity(@MappingTarget NhaCungCap nhaCungCap, NhaCungCapRequest updateNhaCungCapRequest);
}
