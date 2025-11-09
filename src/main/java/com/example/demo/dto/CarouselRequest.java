package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class CarouselRequest {

    @NotBlank
    @Size(max = 255)
    private String title;

    @NotBlank
    @Size(max = 1024)
    private String pic; // 前端字段名为 pic

    private Target target;

    private Integer sort = 0; // 前端使用 sort

    private Boolean active = true;

    // ISO8601 带 Z，Jackson 默认支持 Instant
    private Instant startAt;
    private Instant endAt;

    public CarouselRequest() {}

    public static class Target {
        private String type;
        private String id;

        public Target() {}
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
    }

    // getters / setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPic() { return pic; }
    public void setPic(String pic) { this.pic = pic; }

    public Target getTarget() { return target; }
    public void setTarget(Target target) { this.target = target; }

    public Integer getSort() { return sort; }
    public void setSort(Integer sort) { this.sort = sort; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Instant getStartAt() { return startAt; }
    public void setStartAt(Instant startAt) { this.startAt = startAt; }

    public Instant getEndAt() { return endAt; }
    public void setEndAt(Instant endAt) { this.endAt = endAt; }
}