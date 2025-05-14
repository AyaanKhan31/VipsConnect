package com.vips.vipsconnect.controllers;

import com.vips.vipsconnect.models.CourseMaterial;
import com.vips.vipsconnect.repositories.CourseMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class CourseMaterialController {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @PostMapping("/upload")
    public String uploadMaterial(@RequestBody CourseMaterial material) {
        courseMaterialRepository.save(material);
        return "Material uploaded successfully!";
    }

    @GetMapping("/all")
    public List<CourseMaterial> getAllMaterials() {
        return courseMaterialRepository.findAll();
    }
}