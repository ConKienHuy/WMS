package com.sgu.spring.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phieunhap")
public class PhieuNhap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "so_phieu_nhap")
    private String soPhieuNhap;

    @ManyToOne
    @JoinColumn(name = "kho_id")
    private Kho kho;

    @ManyToOne
    @JoinColumn(name = "nha_cung_cap_id")
    private NhaCungCap nhaCungCap;

    @Column(name = "ngay_nhap_kho")
    private LocalDateTime ngayNhapKho;

    @OneToMany(mappedBy = "phieuNhap")
    private List<ChiTietPhieuNhap> chiTietPhieuNhapList;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "ngay_cap_nhat")
    private LocalDateTime ngayCapNhat;

    @Column(name = "trang_thai")
    private boolean isEnable;
}
