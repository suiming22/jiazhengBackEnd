package com.example.demo.service;

import com.example.demo.dto.CarouselRequest;
import com.example.demo.dto.CarouselResponse;
import com.example.demo.entity.Carousel;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CarouselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * 轮播图业务服务层
 * 负责处理轮播图的增删改查与分页逻辑，调用 Repository 完成持久化操作，
 * 并在返回给控制层前将实体转换为 DTO（CarouselResponse）。
 */
@Service
public class CarouselService {

    private final CarouselRepository repository;

    /**
     * 通过构造器注入 Repository，便于单元测试与依赖管理。
     */
    @Autowired
    public CarouselService(CarouselRepository repository) {
        this.repository = repository;
    }

    /**
     * 创建一个新的轮播图记录。
     * 将请求 DTO 的字段复制到实体，保存后转换为响应 DTO 返回。
     *
     * @param req 创建请求 DTO
     * @return 保存后的响应 DTO
     */
    public CarouselResponse create(CarouselRequest req) {
        Carousel c = new Carousel();
        // 从请求填充实体
        c.setTitle(req.getTitle());
        c.setImageUrl(req.getImageUrl());
        c.setLinkUrl(req.getLinkUrl());
        c.setSortOrder(req.getSortOrder());
        c.setActive(req.getActive());
        // 保存到数据库
        Carousel saved = repository.save(c);
        // 转换为响应 DTO
        return toResponse(saved);
    }

    /**
     * 分页列出轮播图。
     * 使用 Spring Data 的分页与排序支持，将实体页映射为响应 DTO 页。
     *
     * @param page 页号（从 0 开始）
     * @param size 页大小
     * @param sort 排序方式
     * @return 分页的响应 DTO
     */
    public Page<CarouselResponse> list(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Carousel> p = repository.findAll(pageable);
        // 将 Page<Carousel> 映射为 Page<CarouselResponse>
        return p.map(this::toResponse);
    }

    /**
     * 根据 ID 获取单个轮播图。
     * 若未找到则抛出 ResourceNotFoundException。
     *
     * @param id 轮播图 ID
     * @return 响应 DTO
     */
    public CarouselResponse getById(Long id) {
        Carousel c = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Carousel not found: " + id));
        return toResponse(c);
    }

    /**
     * 更新指定 ID 的轮播图。
     * 先查询存在性，再将请求字段覆盖到实体并保存。
     *
     * @param id  轮播图 ID
     * @param req 更新请求 DTO
     * @return 更新后的响应 DTO
     */
    public CarouselResponse update(Long id, CarouselRequest req) {
        Carousel c = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Carousel not found: " + id));
        // 更新字段
        c.setTitle(req.getTitle());
        c.setImageUrl(req.getImageUrl());
        c.setLinkUrl(req.getLinkUrl());
        c.setSortOrder(req.getSortOrder());
        c.setActive(req.getActive());
        // 保存并返回
        Carousel updated = repository.save(c);
        return toResponse(updated);
    }

    /**
     * 删除指定 ID 的轮播图。
     * 先检查是否存在，不存在则抛出异常。
     *
     * @param id 轮播图 ID
     */
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Carousel not found: " + id);
        }
        repository.deleteById(id);
    }

    /**
     * 将实体转换为响应 DTO。
     * 单独抽取为方法方便复用与统一字段映射。
     *
     * @param c 实体对象
     * @return 对应的响应 DTO
     */
    private CarouselResponse toResponse(Carousel c) {
        CarouselResponse r = new CarouselResponse();
        r.setId(c.getId());
        r.setTitle(c.getTitle());
        r.setImageUrl(c.getImageUrl());
        r.setLinkUrl(c.getLinkUrl());
        r.setSortOrder(c.getSortOrder());
        r.setActive(c.getActive());
        // 传递时间戳字段（创建/更新）
        r.setCreatedAt(c.getCreatedAt());
        r.setUpdatedAt(c.getUpdatedAt());
        return r;
    }
}