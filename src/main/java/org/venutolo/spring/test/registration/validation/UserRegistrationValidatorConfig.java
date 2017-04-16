package org.venutolo.spring.test.registration.validation;

public interface UserRegistrationValidatorConfig {

    int getFirstNameMaxLength();

    int getLastNameMaxLength();

    int getMinAge();

    int getMaxAge();

}
