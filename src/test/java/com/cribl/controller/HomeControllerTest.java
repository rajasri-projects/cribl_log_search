package com.cribl.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.web.servlet.ModelAndView;

import org.junit.jupiter.api.Test;

public class HomeControllerTest {

    final HomeController homeController = new HomeController();

    @Test
    public void testGetPage() {
        final ModelAndView modelAndView = homeController.getPage();
        assertEquals("home", modelAndView.getViewName());
    }

}
