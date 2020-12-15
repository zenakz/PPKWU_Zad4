package com.example.zad4;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
public class Controller {

    @GetMapping("/search")
    String getSearchResults(String searchString, HttpServletResponse response){
        String url = "https://panoramafirm.pl/szukaj?k="+searchString;
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("script");
            ArrayList<Firm> companies = new ArrayList<>();
            Gson gson = new Gson();

            for (Element element : elements) {
                if (element.attr("type").equals("application/ld+json")&& element.data().contains("LocalBusiness")) {
                    Firm firm = gson.fromJson(element.data(), Firm.class);
                    companies.add(firm);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ok");

        return url;
    }

    @GetMapping("/card")
    void returnCard(HttpServletResponse response){

    }
}
