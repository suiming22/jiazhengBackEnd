package com.example.demo.service;

import com.example.demo.dto.CarouselRequest;
import com.example.demo.dto.CarouselResponse;
import com.example.demo.entity.Carousel;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CarouselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class CarouselService {

    private final CarouselRepository repository;

    @Autowired
    public CarouselService(CarouselRepository repository) {
        this.repository = repository;
    }

    public CarouselResponse create(CarouselRequest req) {
        Carousel c = new Carousel();
        c.setTitle(req.getTitle());
        c.setImageUrl(req.getImageUrl());
        c.setLinkUrl(req.getLinkUrl());
        c.setSortOrder(req.getSortOrder());
        c.setActive(req.getActive());
        Carousel saved = repository.save(c);
        return toResponse(saved);
    }

    public Page<CarouselResponse> list(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Carousel> p = repository.findAll(pageable);
        return p.map(this::toResponse);
    }

    public CarouselResponse getById(Long id) {
        Carousel c = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Carousel not found: " + id));
        return toResponse(c);
    }

    public CarouselResponse update(Long id, CarouselRequest req) {
        Carousel c = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Carousel not found: " + id));
        c.setTitle(req.getTitle());
        c.setImageUrl(req.getImageUrl());
        c.setLinkUrl(req.getLinkUrl());
        c.setSortOrder(req.getSortOrder());
        c.setActive(req.getActive());
        Carousel updated = repository.save(c);
        return toResponse(updated);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Carousel not found: " + id);
        }
        repository.deleteById(id);
    }

    private CarouselResponse toResponse(Carousel c) {
        CarouselResponse r = new CarouselResponse();
        r.setId(c.getId());
        r.setTitle(c.getTitle());
        r.setImageUrl(c.getImageUrl());
        r.setLinkUrl(c.getLinkUrl());
        r.setSortOrder(c.getSortOrder());
        r.setActive(c.getActive());
        r.setCreatedAt(c.getCreatedAt());
        r.setUpdatedAt(c.getUpdatedAt());
        return r;
    }
}