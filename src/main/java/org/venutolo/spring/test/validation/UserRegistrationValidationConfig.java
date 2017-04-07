package org.venutolo.spring.test.validation;

public interface UserRegistrationValidationConfig {

    int getFirstNameMaxLength();

    int getLastNameMaxLength();

    int getMinAge();

    int getMaxAge();

}
