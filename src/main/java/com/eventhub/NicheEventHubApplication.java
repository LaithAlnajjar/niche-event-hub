package com.eventhub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@SpringBootApplication
public class NicheEventHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(NicheEventHubApplication.class, args);
	}

	@Component
	class DatabaseTestRunner implements CommandLineRunner {

		private final UserRepository userRepository;
		private final EventRepository eventRepository;

		public DatabaseTestRunner(UserRepository userRepository, EventRepository eventRepository) {
			this.userRepository = userRepository;
			this.eventRepository = eventRepository;
		}

		@Override
		public void run(String... args) throws Exception {
			User testUser = new User("test_user", "test@example.com");
			userRepository.save(testUser);
			System.out.println("Saved user: " + testUser.getUsername());
			Event testEvent = new Event(
					"My First Tech Meetup",
					"A test event for our Spring Boot app.",
					LocalDateTime.now().plusDays(7),
					"123 Main St",
					50
			);
			eventRepository.save(testEvent);
			System.out.println("Saved event: " + testEvent.getEventName());
		}
	}

}
