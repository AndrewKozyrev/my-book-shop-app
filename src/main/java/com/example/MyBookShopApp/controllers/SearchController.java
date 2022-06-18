package com.example.MyBookShopApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SearchController {

    @GetMapping("/search/{query}")
    public String search(@PathVariable String query) {
        System.out.println(query);
        return "/search/index";
    }
}
