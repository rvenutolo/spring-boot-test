package org.venutolo.spring.test;

import java.time.LocalDate;
import java.util.Objects;

public class User {

    private final String firstName;

    private final String lastName;

    private final int age;

    private final float height;

    private final boolean active;

    private final LocalDate registeredOn;

    public User(
            final String firstName,
            final String lastName,
            final int age,
            final float height,
            final boolean active,
            final LocalDate registeredOn
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.active = active;
        this.registeredOn = registeredOn;
    }

    public User(
            final String firstName,
            final String lastName,
            final int age,
            final float height,
            final boolean active
    ) {
        this(firstName, lastName, age, height, active, LocalDate.now());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public float getHeight() {
        return height;
    }

    public boolean getActive() {
        return active;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    @Override
    public String toString() {
        return "User{" +
               "firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", age=" + age +
               ", height=" + height +
               ", active=" + active +
               ", registeredOn=" + registeredOn +
               '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        final User user = (User) o;
        return (age == user.age) &&
               (Float.compare(user.height, height) == 0) &&
               (active == user.active) &&
               Objects.equals(firstName, user.firstName) &&
               Objects.equals(lastName, user.lastName) &&
               Objects.equals(registeredOn, user.registeredOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, height, active, registeredOn);
    }

}
