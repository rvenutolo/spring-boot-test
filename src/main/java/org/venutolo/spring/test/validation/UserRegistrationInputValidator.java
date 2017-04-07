package org.venutolo.spring.test.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.venutolo.spring.test.form.UserRegistrationInput;

@Component
public class UserRegistrationInputValidator implements Validator {

    private final int firstNameMaxLength;

    private final int lastNameMaxLength;

    private final int minAge;

    private final int maxAge;

    @Autowired
    public UserRegistrationInputValidator(final UserRegistrationValidationConfig config) {
        this.firstNameMaxLength = config.getFirstNameMaxLength();
        this.lastNameMaxLength = config.getLastNameMaxLength();
        this.minAge = config.getMinAge();
        this.maxAge = config.getMaxAge();
    }

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

    private void validateFirstName(final String firstName, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "invalid.firstName.empty");
        if (firstName.length() > firstNameMaxLength) {
            errors.rejectValue("firstName", "invalid.firstName.maxLength", new Object[]{firstNameMaxLength}, "");
        }
    }

    private void validateLastName(final String lastName, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "invalid.lastName.empty");
        if (lastName.length() > lastNameMaxLength) {
            errors.rejectValue("lastName", "invalid.lastName.maxLength", new Object[]{lastNameMaxLength}, "");
        }
    }

    private void validateAge(final String age, final Errors errors) {
        try {
            final int ageInt = Integer.parseInt(age);
            if ((ageInt < minAge) || (ageInt > maxAge)) {
                errors.rejectValue("age", "invalid.age.minMax", new Object[]{minAge, maxAge}, "");
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
