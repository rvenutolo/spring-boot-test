package org.venutolo.spring.test.form;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserRegistrationInputValidator implements Validator {

    private static final int MIN_AGE = 10;

    private static final int MAX_AGE = 35;

    @Override
    public boolean supports(final Class<?> clazz) {
        return UserRegistrationInput.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final UserRegistrationInput user = (UserRegistrationInput) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "invalid.firstName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "invalid.lastName.empty");
        try {
            final int ageInt = Integer.parseInt(user.getAge());
            if ((ageInt < MIN_AGE) || (ageInt > MAX_AGE)) {
                errors.rejectValue("age", "invalid.age.minMax", new Object[]{MIN_AGE, MAX_AGE}, "");
            }
        } catch (final NumberFormatException e) {
            errors.rejectValue("age", "invalid.age.notNumber");
        }
        try {
            final float heightFloat = Float.parseFloat(user.getHeight());
            if (heightFloat <= 0) {
                errors.rejectValue("height", "invalid.height.lessThanZero");
            }
        } catch (final NumberFormatException e) {
            errors.rejectValue("height", "invalid.height.notDecimal");
        }
    }

}