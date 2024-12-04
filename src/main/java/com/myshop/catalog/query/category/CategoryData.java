package com.myshop.catalog.query.category;

import com.myshop.catalog.command.domain.category.CategoryId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryData {
    @EmbeddedId
    private CategoryId id;

    @Column(name = "name")
    private String name;

    protected CategoryData() {
    }

    public CategoryData(CategoryId id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
