package com.eventhub;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByUserAndEvent(User user, Event event);
}
