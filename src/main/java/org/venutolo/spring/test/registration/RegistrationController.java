package org.venutolo.spring.test.registration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.venutolo.spring.test.User;
import org.venutolo.spring.test.registration.validation.UserRegistrationValidator;
import org.venutolo.spring.test.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private static final String FORM_VIEW = "registration/form";

    private static final String SUCCESS_VIEW = "registration/success";

    private static final Log logger = LogFactory.getLog(RegistrationController.class);

    private final UserRegistrationValidator validator;

    private final UserService service;

    @Autowired
    public RegistrationController(final UserRegistrationValidator validator, final UserService service) {
        this.validator = validator;
        this.service = service;
    }

    @InitBinder
    public void initBinder(final WebDataBinder webDataBinder) {
        webDataBinder.setValidator(validator);
    }

    @GetMapping
    public ModelAndView registrationForm() {
        final ModelAndView modelAndView = new ModelAndView(FORM_VIEW);
        modelAndView.addObject("user", new UserRegistration());
        modelAndView.addObject("users", service.getUsers());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView registrationSubmit(
            @ModelAttribute("user") @Valid final UserRegistration userRegistration,
            final BindingResult bindingResult
    ) {
        logger.debug("Submitted user registration: " + userRegistration);
        final String view;
        if (bindingResult.hasErrors()) {
            logger.debug("User registration is invalid: " + userRegistration);
            view = FORM_VIEW;
        } else {
            logger.debug("User registration is valid: " + userRegistration);
            final User user = userRegistration.toUser();
            final boolean userAdded = service.addUser(user);
            if (userAdded) {
                logger.debug("Added user:  " + user);
                view = SUCCESS_VIEW;
            } else {
                logger.debug("User already exists: " + user);
                bindingResult.reject("invalid.user.exists");
                view = FORM_VIEW;
            }
        }
        final ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject("users", service.getUsers());
        return modelAndView;
    }

}
