package com.sgu.spring.dtos.khos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class KhoRequest {
    @NotBlank(message = "Vui lòng nhập tên kho.")
    private String tenKho;

    private String ghiChu;
}
