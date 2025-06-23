package com.sgu.spring.dtos.sanphams;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SanPhamRequest {

    @NotBlank(message = "Vui lòng nhập mã sản phẩm.")
    private String maSanPham;

    @NotBlank(message = "Vui lòng nhập tến sản phẩm.")
    private String tenSanPham;

    @NotNull(message = "Vui lòng chọn loại sản phẩm.")
    @Min(value = 1, message = "Loại sản phẩm ID phải lớn hơn 0.")
    private Long loaiSanPhamID;

    @NotNull(message = "Vui lòng chọn đơn vị tính.")
    @Min(value = 1, message = "Đơn vị tính ID phải lớn hơn 0.")
    private Long donViTinhID;

    private String ghiChu;
}
