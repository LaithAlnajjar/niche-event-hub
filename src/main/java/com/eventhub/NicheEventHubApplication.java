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

			if (userRepository.count() == 0) {
				userRepository.save(new User("Alice", "alice@test.com"));
				userRepository.save(new User("Bob", "bob@test.com"));
				userRepository.save(new User("Charlie", "charlie@test.com"));
			}

			if (eventRepository.count() == 0) {

				Event javaWorkshop = new Event(
						"Java for Beginners",
						"Learn the basics of Java 17 and OOP.",
						LocalDateTime.now().plusDays(3),
						"Tech Hub Room A",
						20
				);
				eventRepository.save(javaWorkshop);

				Event exclusiveDinner = new Event(
						"CEO Dinner",
						"A very exclusive dinner.",
						LocalDateTime.now().plusWeeks(1),
						"The Ritz",
						2
				);
				eventRepository.save(exclusiveDinner);

				Event springBootCon = new Event(
						"Spring Boot Conference",
						"Deep dive into Microservices.",
						LocalDateTime.now().plusMonths(1),
						"Convention Center",
						100
				);
				eventRepository.save(springBootCon);

				System.out.println("--- 3 Events Created ---");
			}
		}
	}

}
