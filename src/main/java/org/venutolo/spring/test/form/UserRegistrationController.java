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
import org.venutolo.spring.test.form.validation.UserFormValidator;

@Controller
@RequestMapping("/register")
public class UserRegistrationController {

    private static final Log logger = LogFactory.getLog(UserRegistrationController.class);

    private final UserFormValidator validator;

    @Autowired
    public UserRegistrationController(final UserFormValidator validator) {
        this.validator = validator;
    }

    @GetMapping
    public String registrationForm(final Model model) {
        model.addAttribute("user", new UserForm());
        return "registration/registrationForm";
    }

    @PostMapping
    public String registrationSubmit(
            @ModelAttribute("user") final UserForm userForm,
            final Errors errors
    ) {
        logger.debug("Submitted user: " + userForm);
        validator.validate(userForm, errors);
        if (errors.hasErrors()) {
            logger.debug("User is invalid: " + userForm);
            return "registration/registrationForm";
        }
        logger.debug("User is valid: " + userForm);
        // TODO session status? set complete?
        return "registration/registrationSuccess";
    }

}
