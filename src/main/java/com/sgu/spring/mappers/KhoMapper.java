package com.sgu.spring.mappers;

import com.sgu.spring.dtos.khos.KhoRequest;
import com.sgu.spring.dtos.khos.KhoResponse;
import com.sgu.spring.models.Kho;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface KhoMapper {

    @Mapping(source = "tenKho", target = "tenKho")
    @Mapping(source = "ghiChu", target = "ghiChu")
    Kho toEntity(KhoRequest addKhoRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "tenKho", target = "tenKho")
    @Mapping(source = "ghiChu", target = "ghiChu")
    KhoResponse toDto(Kho kho);

    @Mapping(source = "tenKho", target = "tenKho")
    @Mapping(source = "ghiChu", target = "ghiChu")
    Kho updateEntity(@MappingTarget Kho kho, KhoRequest updateKhoRequest);
}
