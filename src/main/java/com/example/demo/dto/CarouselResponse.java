package com.example.demo.dto;

import java.time.Instant;

public class CarouselResponse {

    private Long id;
    private String title;
    private String pic; // 返回字段名 pic
    private Target target;
    private Integer sort;
    private Boolean active;
    private Instant startAt;
    private Instant endAt;
    private Instant createdAt;
    private Instant updatedAt;

    public CarouselResponse() {}

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
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}