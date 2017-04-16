package org.venutolo.spring.test.registration.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("file:validation.properties")
public class PropertySourceUserFormValidatorConfig implements UserFormValidatorConfig {

    private final int firstNameMaxLength;

    private final int lastNameMaxLength;

    private final int minAge;

    private final int maxAge;

    public PropertySourceUserFormValidatorConfig(
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
        return "PropertySourceUserFormValidatorConfig{" +
               "firstNameMaxLength=" + firstNameMaxLength +
               ", lastNameMaxLength=" + lastNameMaxLength +
               ", minAge=" + minAge +
               ", maxAge=" + maxAge +
               '}';
    }

}
