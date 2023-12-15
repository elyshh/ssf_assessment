package vttp.ssf.assessment.eventmanagement.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import vttp.ssf.assessment.eventmanagement.Utils;
import vttp.ssf.assessment.eventmanagement.models.Event;

@Repository
public class RedisRepository {

	@Autowired @Qualifier(Utils.BEAN_REDIS)
    private RedisTemplate<String, String> template;

	private List<String> eventsList = new ArrayList<>();
	
	// TODO: Task 2
	public void saveRecord(Event event) {
		ListOperations<String, String> opsList = template.opsForList();
		opsList.leftPush("events", Event.toJsonString(event));
		eventsList.add(Event.toJsonString(event));
        // valOps.append(event.getId().toString(), Event.toJsonString(event));
	}

	// TODO: Task 3
	public int getNumberOfEvents(){
        return eventsList.size();
    }

	// TODO: Task 4
	public Event getEvent(Integer index) {
		String contents = eventsList.get(index);
		String[] l = contents.split(",");
		
		Integer id = null;
		String name = "";
		Integer size = null;
		Integer date = null;
		Integer participants = null;
		if (l[0].contains("eventId")) {
			String[] line = l[0].split(":");
			id = Integer.parseInt(line[1]);
			// System.out.println(id);
		}
		if (l[1].contains("eventName")) {
			String[] line = l[1].split(":");
			int length = line[1].length();
			String subStr = line[1].substring(1, length-1);
			name = subStr;
			// System.out.println(name);
			
		}
		if (l[2].contains("eventSize")) {
			String[] line = l[2].split(":");
			size = Integer.parseInt(line[1]);
			// System.out.println(size);
		}
		if (l[3].contains("eventDate")) {
			String[] line = l[3].split(":");
			date = Integer.parseInt(line[1]);
			// System.out.println(date);
		}
		if (l[4].contains("participants")) {
			String[] line = l[4].split(":");
			int length = line[1].length();
			String subStr = line[1].substring(0, length-1);
			participants = Integer.parseInt(subStr);
			// System.out.println(participants);
		}
		Event event = new Event(id, name, size, date, participants);
		
		return event;
	}
	
	public Set<String> getAllKeys() {
        Set<String> keys = template.keys("*");
        return keys;
    }

	public List<String> getList() {
		return eventsList;
	}

	public List<Event> convertListToObject() {
		List<String> eventsList = getList();
		List<Event> events = new ArrayList<>();
		for (int i = 0; i < eventsList.size(); i++) {
			events.add(getEvent(i));
		}
		return events;
	}

}
