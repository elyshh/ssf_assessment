package vttp.ssf.assessment.eventmanagement.models;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {
    
    @NotEmpty(message = "Full Name is mandatory")
    @Size(min = 3, max = 20, message = "Full Name must be between 5 to 25 characters")
    private String fullName;

    // Correct format
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    // @Past(message = "Birth date must be a past date less than today")
    // private LocalDate dateOfBirth;

    @Email(message = "Invalid Email Format")
    @Size(max = 50, message = "Email length exceeded 50 characters")
    @NotBlank(message = "Email is a mandatory field")
    private String email;

    @Pattern(regexp = "(8|9)[0-9]{7}", message = "Invalid phone number entered")
    private String mobileNo;

    @Min(value = 1, message = "Minimum of 1 ticket")
    @Max(value = 3, message = "Maximum of 3 tickets")
    private Integer numOfTickets;

    @NotEmpty(message = "Gender is mandatory")
    private String gender;

    // public LocalDate getDateOfBirth() {
    //     return dateOfBirth;
    // }

    // Commented because the date is not converted to date format
    // public static String checkAge(LocalDate dob) {
    //     Integer age = LocalDate.now().getYear() - dob.getYear();
    //     if (age < 21) {
    //         return "Cannot be younger than 21 years old";
    //     }
    //     return null;
    // }
}
