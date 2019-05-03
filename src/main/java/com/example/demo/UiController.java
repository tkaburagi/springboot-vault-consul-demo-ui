package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UiController {

    private final UiService uiService;
    private static Logger log = LoggerFactory.getLogger(UiController.class);

    public UiController(UiService uiService) {
        this.uiService = uiService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String home(String id, Model model) throws Exception {
        log.info("Handling home");
        model.addAttribute("allbooks", this.uiService.getAllBooks());
//        model.addAttribute("searchedBook", this.uiService.getBookById(id));
        return "ui/index";
    }
}