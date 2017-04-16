package org.venutolo.spring.test.registration;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class FormControllerAdvice {

    @InitBinder
    public void registerStringTrimmer(final WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

}
