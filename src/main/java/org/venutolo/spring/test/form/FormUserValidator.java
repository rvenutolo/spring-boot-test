package org.venutolo.spring.test.form;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class FormUserValidator implements Validator {

    private static final int MIN_AGE = 10;

    private static final int MAX_AGE = 35;

    @Override
    public boolean supports(final Class<?> clazz) {
        return FormUser.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final FormUser user = (FormUser) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.firstName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.lastName.empty");
        if ((user.getAge() < MIN_AGE) || (user.getAge() > MAX_AGE)) {
            errors.rejectValue("age", "error.age.minMax", new Object[]{MIN_AGE, MAX_AGE}, "");
        }
    }

}