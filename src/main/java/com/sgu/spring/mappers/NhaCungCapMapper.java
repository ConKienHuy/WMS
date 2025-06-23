package com.sgu.spring.mappers;

import com.sgu.spring.dtos.nhacungcaps.NhaCungCapRequest;
import com.sgu.spring.dtos.nhacungcaps.NhaCungCapResponse;
import com.sgu.spring.models.NhaCungCap;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NhaCungCapMapper {

    NhaCungCap toEntity(NhaCungCapRequest addNhaCungCapRequest);

    NhaCungCapResponse toDto(NhaCungCap nhaCungCap);

    List<NhaCungCapResponse> toDtoList(List<NhaCungCap> nhaCungCapList);

    NhaCungCap updateEntity(@MappingTarget NhaCungCap nhaCungCap, NhaCungCapRequest updateNhaCungCapRequest);
}
