package vttp.ssf.assessment.eventmanagement.controllers;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vttp.ssf.assessment.eventmanagement.models.Register;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    
    @Autowired
    RedisRepository eventsRepo;
    
    // TODO: Task 6
    @PostMapping("/register")
    public ModelAndView processRegistration(@Valid @ModelAttribute("register") Register form, BindingResult result) throws FileNotFoundException {
        ModelAndView mav = new ModelAndView();

        if (result.hasErrors()) {
            mav.setViewName("error");
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }

        // Commented out because the date is not in the correct date format
        // if (null != Register.checkAge(form.getDateOfBirth())) {
        //     System.out.println("Error");
        //     String errorMessage = register.checkAge(form.getDateOfBirth());
        //     FieldError err = new FieldError("register","dateOfBirth", errorMessage);
        //     result.addError(err);
        //     mav.setViewName("error");
        //     mav.setStatus(HttpStatus.BAD_REQUEST);
        //     return mav;
        // }

        mav.setViewName("success");
        mav.addObject("register", form);
        mav.setStatus(HttpStatus.CREATED);

        return mav;

    }

    // TODO: Task 7
}
