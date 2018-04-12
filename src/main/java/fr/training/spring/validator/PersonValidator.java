package fr.training.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import fr.training.spring.form.Person;

@Component
public class PersonValidator implements Validator {

    @Override
    public boolean supports(final Class<?> calzz) {
        return Person.class.isAssignableFrom(calzz);
    }

    @Override
    public void validate(final Object obj, final Errors errors) {
        // By default message key matching is <annotation>[.<class>[.<attribut>]]

        // Define specific messages
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "required.dateOfBirth");
    }
}