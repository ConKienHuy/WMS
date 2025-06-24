package com.sgu.spring.controllers;

import com.sgu.spring.dtos.phieunhaps.AddPhieuNhapRequest;
import com.sgu.spring.dtos.phieunhaps.PhieuNhapResponse;
import com.sgu.spring.dtos.phieunhaps.UpdatePhieuNhapRequest;
import com.sgu.spring.services.PhieuNhapService;
import com.sgu.spring.services.impl.PhieuNhapServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/phieunhaps")
public class PhieuNhapController {
    private PhieuNhapService phieuNhapService;

    @Autowired
    public PhieuNhapController(PhieuNhapServiceImpl phieuNhapServiceImpl) {
        this.phieuNhapService = phieuNhapServiceImpl;
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<PhieuNhapResponse> getById(@PathVariable long id) {
        PhieuNhapResponse response = phieuNhapService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<?> create(@RequestBody @Valid AddPhieuNhapRequest addRequest) {
        PhieuNhapResponse response = phieuNhapService.create(addRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhieuNhapResponse> update(@PathVariable long id , @RequestBody @Valid UpdatePhieuNhapRequest updateRequest) {
        PhieuNhapResponse response = phieuNhapService.update(id, updateRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PhieuNhapResponse> delete(@PathVariable long id) {
        PhieuNhapResponse response = phieuNhapService.delete(id);
        return ResponseEntity.ok(response);
    }
}
