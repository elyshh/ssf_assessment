package vttp.ssf.assessment.eventmanagement.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;

@Service
public class DatabaseService {

    @Autowired
    private RedisRepository eventRepo;
    
    // TODO: Task 1
    public List<Event> readFile(String fileName) throws Exception {

        StringBuilder sb = new StringBuilder();
		try (FileReader fr = new FileReader(fileName)) {
			BufferedReader br = new BufferedReader(fr);
			br.lines().forEach(line -> sb.append(line.trim()));
			JsonReader reader = Json.createReader(new StringReader(sb.toString()));
			JsonArray jsonArray = reader.readArray();
            
            return jsonArray.stream()
                .map(j -> j.asJsonObject())
                .map(o -> {
                    Integer id = o.getInt("eventId");
                    String name = o.getString("eventName");
                    Integer size = o.getInt("eventSize");
                    Integer date = o.getInt("eventDate");
                    Integer participants = o.getInt("participants");
                    return new Event(id, name, size, date, participants);
                })
                .toList();

        }
    }
}
