package fr.training.jstl.controllers;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JSTLController {
    private static final Logger LOGGER = LoggerFactory.getLogger(JSTLController.class.getName());

    @Autowired
    ServletContext servletContext;

    @PostConstruct
    public void init() {
        LOGGER.info("Post construction of that bean");
    }

    @RequestMapping(value = "/core_tags", method = RequestMethod.GET)
    public ModelAndView coreTags(final Model model) {
        ModelAndView mv = new ModelAndView("core_tags");
        return mv;
    }

    @RequestMapping(value = "/core_tags_redirect", method = RequestMethod.GET)
    public ModelAndView coreTagsRedirect(final Model model) {
        ModelAndView mv = new ModelAndView("core_tags_redirect");
        return mv;
    }


    @RequestMapping(value = "/formatting_tags", method = RequestMethod.GET)
    public ModelAndView formattingTags(final Model model) {
        ModelAndView mv = new ModelAndView("formatting_tags");
        return mv;
    }

    @RequestMapping(value = "/xml_tags", method = RequestMethod.GET)
    public ModelAndView xmlTags(final Model model) {
        ModelAndView mv = new ModelAndView("xml_tags");
        return mv;
    }

    @RequestMapping(value = "/items_xml", method = RequestMethod.GET)
    @ResponseBody public FileSystemResource getFile(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/xml");
        return new FileSystemResource(new File(servletContext.getRealPath("/WEB-INF/items.xsl")));
    }

    @RequestMapping(value = "/function_tags", method = RequestMethod.GET)
    public ModelAndView functionTags(final Model model) {
        ModelAndView mv = new ModelAndView("function_tags");
        return mv;
    }
}
