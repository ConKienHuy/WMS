package com.sgu.spring.dtos.loaisanphams;

import lombok.Data;

@Data
public class LoaiSanPhamResponse {
    private Long id;
    private String maLSP;
    private String tenLSP;
    private String ghiChu;
}
