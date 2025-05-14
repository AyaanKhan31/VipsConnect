package com.vips.vipsconnect.controllers;

import com.vips.vipsconnect.models.Announcement;
import com.vips.vipsconnect.repositories.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {

    @Autowired
    private AnnouncementRepository announcementRepository;

//    @PostMapping("/create")
//    public ResponseEntity<?> createAnnouncement(@RequestBody Announcement announcement) {
//        // Save announcement logic
//        return ResponseEntity.ok("Announcement created");
//    }
@PostMapping("/create")
public ResponseEntity<?> createAnnouncement(@RequestBody Announcement announcement) {
    // Ensure createdAt is set to the current time if not already done
    if (announcement.getCreatedAt() == null)    {
        announcement.setCreatedAt(LocalDateTime.now());
    }
    // Save the announcement to the database
    announcementRepository.save(announcement);
    return ResponseEntity.ok("Announcement created successfully");
}


    @GetMapping("/all")
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        List<Announcement> announcements = announcementRepository.findAll();
        return ResponseEntity.ok(announcements);
    }

}
