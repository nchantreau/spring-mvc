package fr.training.spring.form;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Person {

    private long id;

    @Size(min = 2, max = 30)
    private String name;
    @Email(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$")
    private String email;
    @NotNull
    private String dateOfBirth;
    @NotEmpty
    private String password;
    private String country;
    @NotEmpty
    private List<String> favouriteLanguage;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public List<String> getFavouriteLanguage() {
        return favouriteLanguage;
    }

    public void setFavouriteLanguage(final List<String> favouriteLanguage) {
        this.favouriteLanguage = favouriteLanguage;
    }

}
