package com.sgu.spring.dtos.sanphams;

import com.sgu.spring.dtos.donvitinhs.DonViTinhResponse;
import com.sgu.spring.dtos.loaisanphams.LoaiSanPhamResponse;
import lombok.Data;

@Data
public class SanPhamResponse {

    private Long id;

    private String maSanPham;

    private String tenSanPham;

    private LoaiSanPhamResponse loaiSanPham;

    private DonViTinhResponse donViTinh;

    private String ghiChu;
}
