package com.eventhub;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository,EventRepository eventRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<Event> getEventsForUser(Long userId) {
        return bookingRepository.findEventsByUserId(userId);
    }

    @Transactional
    public void registerUserForEvent(long userId, long eventId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventId));

        if (event.getAttendeeCount() >= event.getCapacity()) {
            throw new RuntimeException("Event capacity limit reached.");
        }

        if (bookingRepository.existsByUserAndEvent(user, event)) {
            throw new RuntimeException("User is already registered for this event.");
        }

        Booking booking = new Booking(user, event, LocalDateTime.now(), "CONFIRMED");
        bookingRepository.save(booking);

        event.setAttendeeCount(event.getAttendeeCount() + 1);

        eventRepository.save(event);

    }


}
