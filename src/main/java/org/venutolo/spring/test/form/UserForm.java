package org.venutolo.spring.test.form;

import org.venutolo.spring.test.User;

public class UserForm {

    private String firstName;

    private String lastName;

    private String age;

    private String height;

    private boolean active = true;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(final String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(final String height) {
        this.height = height;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "UserForm{" +
               "firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", age='" + age + '\'' +
               ", height='" + height + '\'' +
               ", active=" + active +
               '}';
    }

    public User toUser() {
        return new User(
                firstName,
                lastName,
                Integer.parseInt(age),
                Float.parseFloat(height),
                active
        );
    }

}
