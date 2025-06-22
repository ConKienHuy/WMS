package com.sgu.spring.dtos.donvitinhs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DonViTinhRequest {
    @NotBlank(message = "Tên đơn vị tính không được để trống.")
    private String tenDonViTinh;

    private String ghiChu;
}
