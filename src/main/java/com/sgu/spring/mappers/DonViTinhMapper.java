package com.sgu.spring.mappers;

import com.sgu.spring.dtos.donvitinhs.DonViTinhRequest;
import com.sgu.spring.dtos.donvitinhs.DonViTinhResponse;
import com.sgu.spring.models.DonViTinh;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DonViTinhMapper {

    @Mapping(source = "tenDonViTinh", target = "tenDonViTinh")
    @Mapping(source = "ghiChu", target = "ghiChu")
    DonViTinh toEntity(DonViTinhRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "tenDonViTinh", target = "tenDonViTinh")
    @Mapping(source = "ghiChu", target = "ghiChu")
    DonViTinhResponse toDto(DonViTinh entity);

    @Mapping(source = "tenDonViTinh", target = "tenDonViTinh")
    @Mapping(source = "ghiChu", target = "ghiChu")
    void updateDonViTinh(@MappingTarget DonViTinh donViTinh, DonViTinhRequest updateDonViTinhRequest);
}