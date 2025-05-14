package com.vips.vipsconnect.controllers;

import com.vips.vipsconnect.models.Alumni;
import com.vips.vipsconnect.repositories.AlumniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumni")
@CrossOrigin(origins = "*") // Optional: allow frontend to access this API
public class AlumniController {

    @Autowired
    private AlumniRepository alumniRepository;

    // Create new alumni profile
    @PostMapping("/add")
    public Alumni addAlumni(@RequestBody Alumni alumni) {
        return alumniRepository.save(alumni);
    }

    // Get all alumni profiles
    @GetMapping("/all")
    public List<Alumni> getAllAlumni() {
        return alumniRepository.findAll();
    }
}
