package com.sgu.spring.dtos.loaisanphams;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoaiSanPhamRequest {
    @NotBlank(message = "Vui lòng nhập mã loại sản phẩm.")
    public String maLSP;

    @NotBlank(message = "Vui lòng nhập tên loại sản phẩm.")
    public String tenLSP;

    public String ghiChu;
}
