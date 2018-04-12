package fr.training.spring.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.training.spring.form.Person;
import fr.training.spring.validator.PersonValidator;

@Controller
public class PersonController {

    @Autowired
    PersonValidator validator;

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public ModelAndView showForm(final Model model) {

        initData(model);
        return new ModelAndView("personForm", "person", new Person());
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("person") Person person, final BindingResult result,
            final ModelMap modelMap, final Model model) {

        validator.validate(person, result);

        if (result.hasErrors()) {

            initData(model);
            return "personForm";
        }

        modelMap.addAttribute("person", person);

        return "personView";
    }

    private void initData(final Model model) {

        final List<String> favouriteLanguageItem = new ArrayList<String>();
        favouriteLanguageItem.add("Java");
        favouriteLanguageItem.add("C++");
        favouriteLanguageItem.add("Python");
        favouriteLanguageItem.add("JS");
        favouriteLanguageItem.add("TypeScript");
        model.addAttribute("favouriteLanguageItem", favouriteLanguageItem);

        final Map<String, String> countryItems = new LinkedHashMap<String, String>();
        countryItems.put("FR", "France");
        countryItems.put("US", "United Stated");
        countryItems.put("IT", "Italy");
        countryItems.put("UK", "United Kingdom");
        model.addAttribute("countryItems", countryItems);

    }
}
