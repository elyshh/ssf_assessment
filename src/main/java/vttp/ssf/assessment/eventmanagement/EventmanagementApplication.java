package vttp.ssf.assessment.eventmanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@SpringBootApplication
public class EventmanagementApplication implements CommandLineRunner {

	@Autowired
	private DatabaseService dbSvc;

	@Autowired
	private RedisRepository eventsRepo;

	public static void main(String[] args) {
		SpringApplication.run(EventmanagementApplication.class, args);
	}
	
	// TODO: Task 1
	@Override
	public void run(String... args) throws Exception {
		ApplicationArguments argsOptions = new DefaultApplicationArguments(args);
		if (argsOptions.containsOption("file")) {
			String fileName = argsOptions.getOptionValues("file").get(0);
			List<Event> events = dbSvc.readFile(fileName);
			System.out.println("Data ingested!");
			// System.out.println(events); // For testing
			for (Event event : events) {
				eventsRepo.saveRecord(event);
				System.out.println("Event{eventId=" + event.getId() + ", eventName=" + event.getName() + ", eventSize=" + event.getSize() + ", eventDate=" + event.getDate() + ", participants=" + event.getParticipants() + "}");
			}
			// System.out.println(eventsRepo.getList()); // For testing
			// System.out.println(eventsRepo.getEvent(0)); // For testing
		}
	}

}
