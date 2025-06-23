package com.sgu.spring.controllers;

import com.sgu.spring.dtos.khos.KhoRequest;
import com.sgu.spring.dtos.khos.KhoResponse;
import com.sgu.spring.services.KhoService;
import com.sgu.spring.services.impl.KhoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/khos")
public class KhoController {
    private KhoService khoService;

    @Autowired
    public KhoController(KhoServiceImpl khoService) {
        this.khoService = khoService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<KhoResponse>> getAll() {
        List<KhoResponse> responseList = khoService.getAllActive();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KhoResponse> getById(@PathVariable("id") long id) {
        KhoResponse response = khoService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<KhoResponse> create(@RequestBody @Valid KhoRequest addRequest) {
        KhoResponse response = khoService.create(addRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KhoResponse> update(@PathVariable("id") long id, @RequestBody @Valid KhoRequest updateRequest) {
        KhoResponse response = khoService.update(id, updateRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<KhoResponse> delete(@PathVariable("id") long id) {
        KhoResponse response = khoService.delete(id);
        return ResponseEntity.ok(response);
    }
}
