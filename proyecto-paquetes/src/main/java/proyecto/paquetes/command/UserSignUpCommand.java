package proyecto.paquetes.command;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class UserSignUpCommand implements Serializable {

    @NotNull(message = "Email is required.")
    @NotEmpty(message = "Email is required.")
    @Size(min = ValidationRules.EMAIL_MIN_SIZE, message = "Email address must have at least 6 characters.")
    @Email(message = "error.format.email")
    private String email;

    @NotNull(message = "Password is required.")
    @NotEmpty(message = "Password is required.")
    @Size(min = ValidationRules.PASSWORD_MIN_SIZE, message = "Password must have at least 6 characters.")
    private String password;

    @NotNull(message = "Confirmation password is required.")
    @NotEmpty(message = "Confirmation password is required.")
    @Size(min = ValidationRules.PASSWORD_MIN_SIZE, message = "Password must have at least 6 characters.")
    private String confirmationPassword;

    @Size(max = ValidationRules.FIRST_LAST_NAME_MAX_SIZE, message = "First name must have less than 50 characters")
    @Pattern(regexp = ValidationRules.FIRST_LAST_NAME_REGEX, message = "Invalid characters in first name.")
    private String firstName;

    @Size(max = ValidationRules.FIRST_LAST_NAME_MAX_SIZE, message = "Last name must have less than 50 characters")
    @Pattern(regexp = ValidationRules.FIRST_LAST_NAME_REGEX, message = "Invalid characters in last name.")
    private String lastName;


    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
