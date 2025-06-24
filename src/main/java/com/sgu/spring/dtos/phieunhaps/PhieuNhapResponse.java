package com.sgu.spring.dtos.phieunhaps;

import com.sgu.spring.dtos.khos.KhoResponse;
import com.sgu.spring.dtos.nhacungcaps.NhaCungCapResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PhieuNhapResponse {
    private Long id;

    private String soPhieuNhap;

    private KhoResponse kho;

    private NhaCungCapResponse nhaCungCap;

    private LocalDateTime ngayNhapKho;

    private String ghiChu;
}
