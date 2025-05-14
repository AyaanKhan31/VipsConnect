package com.vips.vipsconnect.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
public class Announcement {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment for primary key
    private Long id;  // This is the primary key

    private String title;
    private String content;
    private LocalDateTime createdAt;

    private String postedByEmail; // Reference to the teacher who posted it (can be User entity)

    public Announcement() {}

    public Announcement(String title, String content, LocalDateTime createdAt, String postedByEmail) {
        this.title = title;
        this.content = content;
        this.createdAt= createdAt;
        this.postedByEmail= postedByEmail;
    }

        // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getPostedByEmail() {
        return postedByEmail;
    }

    public void setPostedByEmail(String postedByEmail) {
        this.postedByEmail = postedByEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
