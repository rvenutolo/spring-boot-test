package org.venutolo.spring.test.form;

public class UserRegistrationInput {

    private String firstName;

    private String lastName;

    private String age;

    private String height;

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

    @Override
    public String toString() {
        return "UserRegistrationInput{" +
               "firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", age='" + age + '\'' +
               ", height='" + height + '\'' +
               '}';
    }

}
