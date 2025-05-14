package com.vips.vipsconnect.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
public class CourseMaterial {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String fileUrl;
    private String uploadedByEmail;

    public CourseMaterial(Long id, String title, String fileUrl, String uploadedByEmail) {
        this.id = id;
        this.title = title;
        this.fileUrl = fileUrl;
        this.uploadedByEmail = uploadedByEmail;
    }

    public CourseMaterial() {

    }

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getUploadedByEmail() {
        return uploadedByEmail;
    }

    public void setUploadedByEmail(String uploadedByEmail) {
        this.uploadedByEmail = uploadedByEmail;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

