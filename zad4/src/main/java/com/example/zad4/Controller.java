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
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @GetMapping("/search")
    List<Firm> getSearchResults(String searchString, HttpServletResponse response){
        String url = "https://panoramafirm.pl/szukaj?k="+searchString;
        List<Firm> companies = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("script");
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

        return companies;
    }

    @GetMapping("/card")
    void returnCard(HttpServletResponse response){

    }
}
