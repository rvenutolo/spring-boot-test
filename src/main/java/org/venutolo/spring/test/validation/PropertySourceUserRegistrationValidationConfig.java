package org.venutolo.spring.test.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:validation.properties")
public class PropertySourceUserRegistrationValidationConfig implements UserRegistrationValidationConfig {

    private final int firstNameMaxLength;

    private final int lastNameMaxLength;

    private final int minAge;

    private final int maxAge;

    public PropertySourceUserRegistrationValidationConfig(
            @Value("${firstName.maxLength}") final int firstNameMaxLength,
            @Value("${lastName.maxLength}") final int lastNameMaxLength,
            @Value("${age.minValue}") final int minAge,
            @Value("${age.maxValue}") final int maxAge
    ) {
        this.firstNameMaxLength = firstNameMaxLength;
        this.lastNameMaxLength = lastNameMaxLength;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

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

    @Override
    public String toString() {
        return "DefaultUserRegistrationValidationConfig{" +
               "firstNameMaxLength=" + firstNameMaxLength +
               ", lastNameMaxLength=" + lastNameMaxLength +
               ", minAge=" + minAge +
               ", maxAge=" + maxAge +
               '}';
    }

}
