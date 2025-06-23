package com.sgu.spring.dtos.nhacungcaps;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NhaCungCapRequest {
    @NotBlank(message = "Vui lòng nhập mã nhà cung cấp.")
    private String maNCC;

    @NotBlank(message = "Vui lòng nhập tên nhà cung cấp.")
    private String tenNCC;

    private String ghiChu;
}
