package vttp.ssf.assessment.eventmanagement.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import static vttp.ssf.assessment.eventmanagement.Utils.*;

public class Event {
    
    private Integer id;
    private String name;
    private Integer size;
    private Integer date;
    private Integer participants;
    
    public Event(Integer id, String name, Integer size, Integer date, Integer participants) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.date = date;
        this.participants = participants;
    }

    public Event() { }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Integer getSize() { return size; }
    public void setSize(Integer size) { this.size = size; }
    
    public Integer getDate() { return date; }
    public void setDate(Integer date) { this.date = date; }
    
    public Integer getParticipants() { return participants; }
    public void setParticipants(Integer participants) { this.participants = participants; }

    public static String toJsonString(Event event){
        JsonObject obj = Json.createObjectBuilder()
                            .add(ATTR_ID, event.getId())
                            .add(ATTR_NAME, event.getName())
                            .add(ATTR_SIZE, event.getSize())
                            .add(ATTR_DATE, event.getDate())
                            .add(ATTR_PARTICIPANTS, event.getParticipants())
                            .build();
        return obj.toString();
    }

}
