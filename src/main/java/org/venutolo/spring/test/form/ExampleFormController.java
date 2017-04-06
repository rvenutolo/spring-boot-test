package org.venutolo.spring.test.form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExampleFormController {

    @GetMapping("/form")
    public String formGet(final Model model) {
        model.addAttribute("user", new FormUser());
        return "formInput";
    }

    @PostMapping("/form")
    public String formPost(@ModelAttribute("user") final FormUser formUser) {
        return "formResult";
    }

}
