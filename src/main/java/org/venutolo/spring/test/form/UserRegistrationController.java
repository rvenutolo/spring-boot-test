package org.venutolo.spring.test.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.venutolo.spring.test.validation.UserRegistrationInputValidator;

@Controller
@RequestMapping("/register")
public class UserRegistrationController {

    private final UserRegistrationInputValidator validator;

    @Autowired
    public UserRegistrationController(final UserRegistrationInputValidator validator) {
        this.validator = validator;
    }

    @GetMapping
    public String registrationForm(final Model model) {
        model.addAttribute("user", new UserRegistrationInput());
        return "registrationForm";
    }

    @PostMapping
    public String registrationSubmit(
            @ModelAttribute("user") final UserRegistrationInput userRegistrationInput,
            final Errors errors
    ) {
        validator.validate(userRegistrationInput, errors);
        if (errors.hasErrors()) {
            return "registrationForm";
        }
        // TODO session status? set complete?
        return "registrationSuccess";
    }

}
