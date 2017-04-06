package org.venutolo.spring.test.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/form")
public class ExampleFormController {

    private final FormUserValidator validator;

    @Autowired
    public ExampleFormController(final FormUserValidator validator) {
        this.validator = validator;
    }

    @GetMapping
    public String formGet(final Model model) {
        model.addAttribute("user", new FormUser());
        return "formInput";
    }

    @PostMapping
    public String formPost(
            @ModelAttribute("user") final FormUser formUser,
            final Errors errors
    ) {
        validator.validate(formUser, errors);
        if (errors.hasErrors()) {
            return "formInput";
        }
        // TODO session status? set complete?
        return "formResult";
    }

}
