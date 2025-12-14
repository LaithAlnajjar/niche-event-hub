package com.eventhub;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // 1. Import Model
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String bookEvent(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null) {
            return "redirect:/login";
        }
        try {
            bookingService.registerUserForEvent(currentUser.getId(), id);
            redirectAttributes.addFlashAttribute("successMessage", "You are booked!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/events/" + id;
    }

    @GetMapping("/my-events")
    public String getMyEvents(@RequestParam Long userId, Model model) {
       List<Event> myEvents = bookingService.getEventsForUser(userId);

        model.addAttribute("events", myEvents);
        model.addAttribute("userId", userId); //
        return "my-dashboard";
    }
}