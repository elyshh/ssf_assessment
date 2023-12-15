package vttp.ssf.assessment.eventmanagement.controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.models.Register;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;

@Controller
@RequestMapping({"/", "/events"})
public class EventController {

	//TODO: Task 5
	@Autowired
	RedisRepository eventRepo;
	

	@GetMapping("/listing")
	public String displayEvents(Model model) {
        model.addAttribute("eventsList", eventRepo.convertListToObject());

        return "listing";
	}

	@GetMapping("/register/{id}")
	public ModelAndView registerEvent(@PathVariable("id") String id) {
		ModelAndView mav = new ModelAndView();
		Register r = new Register();
		mav.setViewName("eventregister");
		mav.addObject("register", r);
		mav.setStatus(HttpStatus.OK);
        return mav;
	}

}
