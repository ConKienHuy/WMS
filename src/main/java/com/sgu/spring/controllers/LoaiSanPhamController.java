package com.sgu.spring.controllers;

import com.sgu.spring.dtos.loaisanphams.LoaiSanPhamRequest;
import com.sgu.spring.dtos.loaisanphams.LoaiSanPhamResponse;
import com.sgu.spring.services.LoaiSanPhamService;
import com.sgu.spring.services.impl.LoaiSanPhamServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loaisanphams")
public class LoaiSanPhamController {
    private LoaiSanPhamService loaiSanPhamService;

    @Autowired
    public LoaiSanPhamController(LoaiSanPhamServiceImpl loaiSanPhamService) {
        this.loaiSanPhamService = loaiSanPhamService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<LoaiSanPhamResponse>> getAll() {
        List<LoaiSanPhamResponse> responseList = loaiSanPhamService.getAllActive();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoaiSanPhamResponse> getById(@PathVariable("id") long id) {
        LoaiSanPhamResponse response = loaiSanPhamService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<LoaiSanPhamResponse> create(@RequestBody @Valid LoaiSanPhamRequest addRequest) {
        LoaiSanPhamResponse response = loaiSanPhamService.create(addRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<LoaiSanPhamResponse> update(@PathVariable("id") long id, @RequestBody @Valid LoaiSanPhamRequest updateRequest) {
        LoaiSanPhamResponse response = loaiSanPhamService.update(id, updateRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LoaiSanPhamResponse> delete(@PathVariable("id") long id) {
        LoaiSanPhamResponse response = loaiSanPhamService.delete(id);
        return ResponseEntity.ok(response);
    }
}
