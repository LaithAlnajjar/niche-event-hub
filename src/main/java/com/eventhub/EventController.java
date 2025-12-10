package com.eventhub;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // 1. Import Model
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EventController {

    private final EventService eventService;
    private final BookingService bookingService;

    public EventController(EventService eventService, BookingService bookingService) {
        this.eventService = eventService;
        this.bookingService = bookingService;
    }

    @GetMapping("/events")
    public String getAllEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "events-list";
    }

    @GetMapping("/events/{id}")
    public String getEvent(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        model.addAttribute("event", event);

        return "event-detail";
    }


    @PostMapping("/events/{id}/register")
    public String bookEvent(@PathVariable Long id, @RequestParam Long userId) {

        bookingService.registerUserForEvent(userId, id);
        return "redirect:/events";
    }
}