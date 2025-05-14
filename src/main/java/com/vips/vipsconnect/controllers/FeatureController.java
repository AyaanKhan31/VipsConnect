package com.vips.vipsconnect.controllers;

import com.vips.vipsconnect.models.Announcement;
import com.vips.vipsconnect.models.CourseMaterial;
import com.vips.vipsconnect.models.Event;
import com.vips.vipsconnect.models.User;
import com.vips.vipsconnect.repositories.AnnouncementRepository;
import com.vips.vipsconnect.repositories.CourseMaterialRepository;
import com.vips.vipsconnect.repositories.EventRepository;
import com.vips.vipsconnect.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FeatureController {

    @Autowired private AnnouncementRepository announcementRepo;
    @Autowired private EventRepository eventRepo;
    @Autowired private CourseMaterialRepository materialRepo;
    @Autowired private UserRepository userRepo;

    // 1. Post Announcement (TEACHER only)
    @PostMapping("/announcements")
    public ResponseEntity<?> postAnnouncement(Authentication authentication, @RequestBody Announcement announcement) {
        String email = authentication.getName();
        User user = userRepo.findByEmail(email).orElse(null);
        if (user == null || !user.getRole().equalsIgnoreCase("TEACHER")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only teachers can post announcements");
        }
        announcement.setPostedByEmail(email);
        announcement.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok(announcementRepo.save(announcement));
    }


    // 2. View Announcements (ALL)
    @GetMapping("/announcements")
    public List<Announcement> getAllAnnouncements() {
        return announcementRepo.findAll();
    }

    // 3. Upload Course Material (TEACHER only)
    @PostMapping("/materials")
    public ResponseEntity<?> uploadMaterial(Authentication authentication, @RequestBody CourseMaterial material) {
        String email = authentication.getName();
        User user = userRepo.findByEmail(email).orElse(null);
        if (user == null || !user.getRole().equalsIgnoreCase("TEACHER")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only teachers can upload materials");
        }
        material.setUploadedByEmail(email);
        return ResponseEntity.ok(materialRepo.save(material));
    }


    // 4. View Course Materials (ALL)
    @GetMapping("/materials")
    public List<CourseMaterial> getAllMaterials() {
        return materialRepo.findAll();
    }

    // 5. Create Event (TEACHER only)
    @PostMapping("/events")
    public ResponseEntity<?> createEvent(Authentication authentication, @RequestBody Event event) {
        String email = (String) authentication.getName();
        User user = userRepo.findByEmail(email).orElse(null);
        if (user == null || !user.getRole().equalsIgnoreCase("TEACHER")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only teachers can create events");
        }
        event.setCreatedByEmail(email);
        return ResponseEntity.ok(eventRepo.save(event));
    }


    // 6. View Events (ALL)
    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }
}
