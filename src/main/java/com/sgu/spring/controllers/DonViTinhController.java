package com.sgu.spring.controllers;

import com.sgu.spring.dtos.donvitinhs.DonViTinhRequest;
import com.sgu.spring.dtos.donvitinhs.DonViTinhResponse;
import com.sgu.spring.services.DonViTinhService;
import com.sgu.spring.services.impl.DonViTinhServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donvitinhs")
public class DonViTinhController {
    private DonViTinhService donViTinhService;

    @Autowired
    public DonViTinhController(DonViTinhServiceImpl donViTinhService) {
        this.donViTinhService = donViTinhService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<DonViTinhResponse>> getAll() {
        List<DonViTinhResponse> responseList = donViTinhService.getAllActive();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonViTinhResponse> getById(@PathVariable("id") long id) {
        DonViTinhResponse response = donViTinhService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<DonViTinhResponse> create(@RequestBody @Valid DonViTinhRequest addRequest) {
        DonViTinhResponse response = donViTinhService.create(addRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<DonViTinhResponse> update(@PathVariable("id") long id, @RequestBody @Valid DonViTinhRequest updateRequest) {
        DonViTinhResponse response = donViTinhService.update(id, updateRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DonViTinhResponse> delete(@PathVariable("id") long id) {
        DonViTinhResponse response = donViTinhService.delete(id);
        return ResponseEntity.ok(response);
    }
}
