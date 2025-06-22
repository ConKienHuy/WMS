package com.sgu.spring.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "donvitinh")
public class DonViTinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String tenDonViTinh;

    public String ghiChu;

    public LocalDateTime ngayTao;

    public LocalDateTime ngayCapNhat;

    public boolean isEnable;
}
