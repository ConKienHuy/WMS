package com.sgu.spring.dtos.phieunhaps;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class AddPhieuNhapRequest {

    @NotBlank(message = "Vui lòng nhập số phiếu nhập.")
    private String soPhieuNhap;

    @NotNull(message = "Vui lòng chọn kho.")
    private Long khoID;

    @NotNull(message = "Vui lòng chọn nhà cung cấp.")
    private Long nccID;

    @Valid
    private List<CTPNRequest> chiTietPhieuNhap = new ArrayList<>();

    @NotNull(message = "Vui lòng nhập ngày nhập kho.")
    private LocalDateTime ngayNhapKho;

    private String ghiChu;
}
