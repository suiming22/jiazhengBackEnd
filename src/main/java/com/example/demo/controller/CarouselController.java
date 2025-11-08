package com.example.demo.controller;

import com.example.demo.dto.CarouselRequest;
import com.example.demo.dto.CarouselResponse;
import com.example.demo.service.CarouselService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/carousels")
public class CarouselController {

    private final CarouselService service;

    @Autowired
    public CarouselController(CarouselService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CarouselResponse> create(@Valid @RequestBody CarouselRequest req, UriComponentsBuilder uriBuilder) {
        CarouselResponse created = service.create(req);
        URI location = uriBuilder.path("/api/carousels/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<?> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "sortOrder,asc") String sort) {

        String[] sortParts = sort.split(",");
        Sort.Direction dir = Sort.Direction.fromString(sortParts.length > 1 ? sortParts[1] : "asc");
        String prop = sortParts.length > 0 ? sortParts[0] : "sortOrder";
        var result = service.list(page, size, Sort.by(dir, prop));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarouselResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarouselResponse> update(@PathVariable Long id, @Valid @RequestBody CarouselRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}