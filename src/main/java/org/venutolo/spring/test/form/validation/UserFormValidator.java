package org.venutolo.spring.test.form.validation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.venutolo.spring.test.form.UserForm;

@Component
public class UserFormValidator implements Validator {

    private static final Log logger = LogFactory.getLog(UserFormValidator.class);

    private final int firstNameMaxLength;

    private final int lastNameMaxLength;

    private final int minAge;

    private final int maxAge;

    @Autowired
    public UserFormValidator(final UserFormValidationConfig config) {
        this.firstNameMaxLength = config.getFirstNameMaxLength();
        this.lastNameMaxLength = config.getLastNameMaxLength();
        this.minAge = config.getMinAge();
        this.maxAge = config.getMaxAge();
    }

    @Override
    public boolean supports(final Class<?> clazz) {
        return UserForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final UserForm user = (UserForm) target;
        logger.debug("Validating user: " + user);
        validateFirstName(user.getFirstName(), errors);
        validateLastName(user.getLastName(), errors);
        validateAge(user.getAge(), errors);
        validateHeight(user.getHeight(), errors);
        logger.debug(errors.hasErrors()
                     ? ("User is invalid -- User: + " + user + "; Errors:" + errors)
                     : ("User is valid -- User: + " + user));
    }

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