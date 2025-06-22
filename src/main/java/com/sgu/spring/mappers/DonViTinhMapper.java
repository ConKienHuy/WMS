package com.sgu.spring.mappers;

import com.sgu.spring.dtos.donvitinhs.DonViTinhRequest;
import com.sgu.spring.dtos.donvitinhs.DonViTinhResponse;
import com.sgu.spring.models.DonViTinh;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DonViTinhMapper {

    DonViTinh toEntity(DonViTinhRequest addDonViTinhRequest);

    DonViTinhResponse toDto(DonViTinh donViTinh);

    List<DonViTinhResponse> toDtoList(List<DonViTinh> donViTinhList);

    DonViTinh updateEntity(@MappingTarget DonViTinh donViTinh, DonViTinhRequest updateDonViTinhRequest);
}
