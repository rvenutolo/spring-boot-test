package org.venutolo.spring.test;

import java.time.LocalDate;
import java.util.Objects;

public class User {

    private final String firstName;

    private final String lastName;

    private final int age;

    private final float height;

    private final LocalDate registeredOn;

    public User(final String firstName, final String lastName, final int age, final float height) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.registeredOn = LocalDate.now();
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

    public LocalDate getRegisteredOn() {
        return registeredOn;
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
               Objects.equals(firstName, user.firstName) &&
               Objects.equals(lastName, user.lastName) &&
               Objects.equals(registeredOn, user.registeredOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, height, registeredOn);
    }

    @Override
    public String toString() {
        return "User{" +
               "firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", age=" + age +
               ", height=" + height +
               ", registeredOn=" + registeredOn +
               '}';
    }

}
