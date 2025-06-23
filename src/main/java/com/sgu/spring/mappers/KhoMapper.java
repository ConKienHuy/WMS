package com.sgu.spring.mappers;

import com.sgu.spring.dtos.khos.KhoRequest;
import com.sgu.spring.dtos.khos.KhoResponse;
import com.sgu.spring.models.Kho;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KhoMapper {

    Kho toEntity(KhoRequest addKhoRequest);

    KhoResponse toDto(Kho kho);

    List<KhoResponse> toDtoList(List<Kho> khoList);

    Kho updateEntity(@MappingTarget Kho kho, KhoRequest updateKhoRequest);
}
