package com.eventhub;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b.event FROM Booking b WHERE b.user.id = ?1")
    List<Event> findEventsByUserId(Long userId);

    @Query("SELECT b.user FROM Booking b WHERE b.event.id = ?1")
    List<User> findUsersByEventId(Long eventId);

    boolean existsByUserAndEvent(User user, Event event);
}
