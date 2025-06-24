package com.sgu.spring.dtos.phieunhaps;

import com.sgu.spring.dtos.sanphams.SanPhamResponse;
import lombok.Data;

@Data
public class CTPNResponse {

    private SanPhamResponse sanPham;

    private long SLNhap;

    private double donGiaNhap;
}
