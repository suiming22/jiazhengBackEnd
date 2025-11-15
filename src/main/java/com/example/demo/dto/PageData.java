package com.example.demo.dto;

import java.util.List;

public class PageData<T> {
    private List<T> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public static <T> PageData<T> of(List<T> content, int pageNumber, int pageSize, long totalElements, int totalPages) {
        PageData<T> pageData = new PageData<>();
        pageData.setContent(content);
        pageData.setPageNumber(pageNumber);
        pageData.setPageSize(pageSize);
        pageData.setTotalElements(totalElements);
        pageData.setTotalPages(totalPages);
        return pageData;
    }
}
