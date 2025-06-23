package com.sgu.spring.controllers;

import com.sgu.spring.dtos.nhacungcaps.NhaCungCapRequest;
import com.sgu.spring.dtos.nhacungcaps.NhaCungCapResponse;
import com.sgu.spring.services.NhaCungCapService;
import com.sgu.spring.services.impl.NhaCungCapServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nhacungcaps")
public class NhaCungCapController {
    private NhaCungCapService nhaCungCapService;

    @Autowired
    public NhaCungCapController(NhaCungCapServiceImpl nhaCungCapService) {
        this.nhaCungCapService = nhaCungCapService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<NhaCungCapResponse>> getAll() {
        List<NhaCungCapResponse> responseList = nhaCungCapService.getAllActive();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NhaCungCapResponse> getById(@PathVariable("id") long id) {
        NhaCungCapResponse response = nhaCungCapService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<NhaCungCapResponse> create(@RequestBody @Valid NhaCungCapRequest addRequest) {
        NhaCungCapResponse response = nhaCungCapService.create(addRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<NhaCungCapResponse> update(@PathVariable("id") long id, @RequestBody @Valid NhaCungCapRequest updateRequest) {
        NhaCungCapResponse response = nhaCungCapService.update(id, updateRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NhaCungCapResponse> delete(@PathVariable("id") long id) {
        NhaCungCapResponse response = nhaCungCapService.delete(id);
        return ResponseEntity.ok(response);
    }
}
