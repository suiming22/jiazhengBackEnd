package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.CarouselService;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carousels")
public class CarouselController {

    private final CarouselService carouselService;

    public CarouselController(CarouselService carouselService) {
        this.carouselService = carouselService;
    }

    @GetMapping
    public ApiResponse<PageData<CarouselResponse>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "sortOrder,asc") String sort) {

        String[] sortParams = sort.split(",");
        Sort sorting = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);

        PageData<CarouselResponse> pageData = carouselService.list(page, size, sorting);
        return ApiResponse.success(pageData);
    }

    @GetMapping("/{id}")
    public ApiResponse<CarouselResponse> getById(@PathVariable Long id) {
        return ApiResponse.success(carouselService.getById(id));
    }

    @PostMapping
    public ApiResponse<CarouselResponse> create(@RequestBody CarouselRequest request) {
        return ApiResponse.success(carouselService.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<CarouselResponse> update(@PathVariable Long id, @RequestBody CarouselRequest request) {
        return ApiResponse.success(carouselService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        carouselService.delete(id);
        return ApiResponse.success(null);
    }
}
