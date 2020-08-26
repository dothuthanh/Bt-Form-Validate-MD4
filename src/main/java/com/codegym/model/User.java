package com.codegym.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



@Component
@Entity
@Table(name = "users")
public class User implements Validator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Size(min = 5, max = 45)
    private String firstname;
    @NotEmpty
    @Size(min = 5, max = 45)
    private String lastname;
    private String number;
    @Min(18)
    private String age;
    @Email
    private String email;

    public User() {
    }

    public User(Long id, @NotEmpty @Size(min = 5, max = 45) String firstname, @NotEmpty @Size(min = 5, max = 45) String lastname, String number, @Min(18) String age, @Email String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.number = number;
        this.age = age;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;
        String number = user.getNumber();
        ValidationUtils.rejectIfEmpty(errors, "number", "number.empty");
        if (number.length() > 11 || number.length() < 10) {
            errors.rejectValue("number", "number.length");
        }
        if (!number.startsWith("0")) {
            errors.rejectValue("number", "number.startsWith");
        }
        if (!number.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("number", "number.matches");

        }
    }
}
