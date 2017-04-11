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
import org.venutolo.spring.test.User;
import org.venutolo.spring.test.form.validation.UserFormValidator;
import org.venutolo.spring.test.service.UserService;

@Controller
@RequestMapping("/register")
public class UserRegistrationController {

    private static final Log logger = LogFactory.getLog(UserRegistrationController.class);

    private final UserFormValidator validator;

    private final UserService service;

    @Autowired
    public UserRegistrationController(final UserFormValidator validator, final UserService service) {
        this.validator = validator;
        this.service = service;
    }

    @GetMapping
    public String registrationForm(final Model model) {
        model.addAttribute("user", new UserForm());
        model.addAttribute("users", service.getUsers());
        return "registration/registrationForm";
    }

    @PostMapping
    public String registrationSubmit(
            @ModelAttribute("user") final UserForm userForm,
            final Errors errors
    ) {
        logger.debug("Submitted user form: " + userForm);
        validator.validate(userForm, errors);
        if (errors.hasErrors()) {
            logger.debug("User form is invalid: " + userForm);
            return "registration/registrationForm";
        }
        logger.debug("User form is valid: " + userForm);
        final User user = userForm.toUser();
        final boolean userAdded = service.addUser(user);
        if (userAdded) {
            logger.debug("Added user:  " + user);
            return "registration/registrationSuccess";
        } else {
            logger.debug("User already exists: " + user);
            errors.reject("invalid.user.exists");
            return "registration/registrationForm";
        }
    }

}
