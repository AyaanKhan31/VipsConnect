package com.vips.vipsconnect.controllers;

import com.vips.vipsconnect.models.Event;
import com.vips.vipsconnect.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    // Post mapping to create an event
    @PostMapping("/create")
    public String createEvent(@RequestBody Event event) {
        eventRepository.save(event);
        return "Event created successfully!";
    }

    // Get mapping to retrieve all events (for students to view)
    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
