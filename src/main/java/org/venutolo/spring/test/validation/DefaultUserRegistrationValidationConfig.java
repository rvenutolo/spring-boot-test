package org.venutolo.spring.test.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:validation.properties")
public class DefaultUserRegistrationValidationConfig implements UserRegistrationValidationConfig {

    @Value("${firstName.maxLength}")
    private int firstNameMaxLength;

    @Value("${lastName.maxLength}")
    private int lastNameMaxLength;

    @Value("${age.minValue}")
    private int minAge;

    @Value("${age.maxValue}")
    private int maxAge;

    @Override
    public int getFirstNameMaxLength() {
        return firstNameMaxLength;
    }

    @Override
    public int getLastNameMaxLength() {
        return lastNameMaxLength;
    }

    @Override
    public int getMinAge() {
        return minAge;
    }

    @Override
    public int getMaxAge() {
        return maxAge;
    }

}
