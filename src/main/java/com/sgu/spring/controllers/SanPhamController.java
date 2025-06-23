package com.sgu.spring.controllers;

import com.sgu.spring.dtos.sanphams.SanPhamRequest;
import com.sgu.spring.dtos.sanphams.SanPhamResponse;
import com.sgu.spring.services.SanPhamService;
import com.sgu.spring.services.impl.SanPhamServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sanphams")
public class SanPhamController {
    private SanPhamService sanPhamService;

    @Autowired
    public SanPhamController(SanPhamServiceImpl sanPhamService) {
        this.sanPhamService = sanPhamService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<SanPhamResponse>> getAll() {
        List<SanPhamResponse> responseList = sanPhamService.getAllActive();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SanPhamResponse> getById(@PathVariable("id") long id) {
        SanPhamResponse response = sanPhamService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<SanPhamResponse> create(@RequestBody @Valid SanPhamRequest addRequest) {
        SanPhamResponse response = sanPhamService.create(addRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SanPhamResponse> update(@PathVariable("id") long id, @RequestBody @Valid SanPhamRequest updateRequest) {
        SanPhamResponse response = sanPhamService.update(id, updateRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SanPhamResponse> delete(@PathVariable("id") long id) {
        SanPhamResponse response = sanPhamService.delete(id);
        return ResponseEntity.ok(response);
    }
}

