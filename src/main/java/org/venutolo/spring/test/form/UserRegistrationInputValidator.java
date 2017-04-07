package org.venutolo.spring.test.form;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserRegistrationInputValidator implements Validator {

    private static final int FIRST_NAME_MAX_LENGTH = 10;

    private static final int LAST_NAME_MAX_LENGTH = 20;

    private static final int MIN_AGE = 18;

    private static final int MAX_AGE = 50;

    @Override
    public boolean supports(final Class<?> clazz) {
        return UserRegistrationInput.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final UserRegistrationInput user = (UserRegistrationInput) target;
        validateFirstName(user.getFirstName(), errors);
        validateLastName(user.getLastName(), errors);
        validateAge(user.getAge(), errors);
        validateHeight(user.getHeight(), errors);    }

    private static void validateFirstName(final String firstName, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "invalid.firstName.empty");
        if (firstName.length() > FIRST_NAME_MAX_LENGTH) {
            errors.rejectValue("firstName", "invalid.firstName.maxLength", new Object[]{FIRST_NAME_MAX_LENGTH}, "");
        }
    }

    private static void validateLastName(final String lastName, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "invalid.lastName.empty");
        if (lastName.length() > LAST_NAME_MAX_LENGTH) {
            errors.rejectValue("lastName", "invalid.lastName.maxLength", new Object[]{LAST_NAME_MAX_LENGTH}, "");
        }
    }

    private static void validateAge(final String age, final Errors errors) {
        try {
            final int ageInt = Integer.parseInt(age);
            if ((ageInt < MIN_AGE) || (ageInt > MAX_AGE)) {
                errors.rejectValue("age", "invalid.age.minMax", new Object[]{MIN_AGE, MAX_AGE}, "");
            }
        } catch (final NumberFormatException e) {
            errors.rejectValue("age", "invalid.age.notNumber");
        }
    }

    private static void validateHeight(final String height, final Errors errors) {
        try {
            final float heightFloat = Float.parseFloat(height);
            if (heightFloat <= 0) {
                errors.rejectValue("height", "invalid.height.lessThanZero");
            }
        } catch (final NumberFormatException e) {
            errors.rejectValue("height", "invalid.height.notDecimal");
        }
    }

}
