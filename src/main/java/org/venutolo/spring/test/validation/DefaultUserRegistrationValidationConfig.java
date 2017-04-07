package org.venutolo.spring.test.validation;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultUserRegistrationValidationConfig implements UserRegistrationValidationConfig {

    private static final int FIRST_NAME_MAX_LENGTH = 10;

    private static final int LAST_NAME_MAX_LENGTH = 20;

    private static final int MIN_AGE = 18;

    private static final int MAX_AGE = 55;

    @Override
    public int getFirstNameMaxLength() {
        return FIRST_NAME_MAX_LENGTH;
    }

    @Override
    public int getLastNameMaxLength() {
        return LAST_NAME_MAX_LENGTH;
    }

    @Override
    public int getMinAge() {
        return MIN_AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

}
