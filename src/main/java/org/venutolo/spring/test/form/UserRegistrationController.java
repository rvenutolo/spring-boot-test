package org.venutolo.spring.test.form;

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
import org.venutolo.spring.test.form.validation.UserFormValidator;
import org.venutolo.spring.test.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class UserRegistrationController {

    private static final String FORM_VIEW = "registration/registrationForm";

    private static final String SUCCESS_VIEW = "registration/registrationSuccess";

    private static final Log logger = LogFactory.getLog(UserRegistrationController.class);

    private final UserFormValidator validator;

    private final UserService service;

    @Autowired
    public UserRegistrationController(final UserFormValidator validator, final UserService service) {
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
        modelAndView.addObject("user", new UserForm());
        modelAndView.addObject("users", service.getUsers());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView registrationSubmit(
            @ModelAttribute("user") @Valid final UserForm userForm,
            final BindingResult bindingResult
    ) {
        logger.debug("Submitted user form: " + userForm);
        final String view;
        if (bindingResult.hasErrors()) {
            logger.debug("User form is invalid: " + userForm);
            view = FORM_VIEW;
        } else {
            logger.debug("User form is valid: " + userForm);
            final User user = userForm.toUser();
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
