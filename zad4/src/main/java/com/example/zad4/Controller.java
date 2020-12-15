package com.example.zad4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class Controller {

    @GetMapping("/search")
    String getSearchResults(String searchString, HttpServletResponse response){
        String url = "https://panoramafirm.pl/szukaj?k="+searchString;
        return url;
    }
    
    @GetMapping("/card")
    void returnCard(HttpServletResponse response){

    }
}
