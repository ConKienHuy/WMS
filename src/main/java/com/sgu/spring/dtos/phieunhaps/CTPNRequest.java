package com.sgu.spring.dtos.phieunhaps;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class CTPNRequest {

    @NotNull(message = "Vui lòng chọn mã sản phẩm.")
    private Long sanPhamID;

    @NotNull(message = "Vui lòng nhạp số lượng nhập.")
    @Range(min = 0, max = Long.MAX_VALUE, message = "Số lượng nhập không hợp lệ.")
    private Long soLuongNhap;

    @NotNull(message = "Vui lòng nhập đơn giá nhập.")
    @DecimalMin(value = "0.0", message = "Đơn giá phải >= 0.")
    @DecimalMax(value = "1000000000", message = "Đơn giá quá lớn.")
    private Double donGiaNhap;
}
