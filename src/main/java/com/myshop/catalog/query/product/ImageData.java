package com.myshop.catalog.query.product;

import jakarta.persistence.Column;
import java.time.LocalDateTime;

public class ImageData {
    @Column(name = "image_path")
    private String path;

    private LocalDateTime uploadTime;

}
