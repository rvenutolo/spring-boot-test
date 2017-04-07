package org.venutolo.spring.test.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private static final Log logger = LogFactory.getLog(UserRegistrationController.class);

    private final UserRegistrationInputValidator validator;

    @Autowired
    public UserRegistrationController(final UserRegistrationInputValidator validator) {
        this.validator = validator;
    }

    @GetMapping
    public String registrationForm(final Model model) {
        model.addAttribute("user", new UserRegistrationInput());
        return "registration/registrationForm";
    }

    @PostMapping
    public String registrationSubmit(
            @ModelAttribute("user") final UserRegistrationInput userRegistrationInput,
            final Errors errors
    ) {
        logger.debug("Submitted user: " + userRegistrationInput);
        validator.validate(userRegistrationInput, errors);
        if (errors.hasErrors()) {
            logger.debug("User is invalid: " + userRegistrationInput);
            return "registration/registrationForm";
        }
        logger.debug("User is valid: " + userRegistrationInput);
        // TODO session status? set complete?
        return "registration/registrationSuccess";
    }

}
