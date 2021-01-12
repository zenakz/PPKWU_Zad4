package com.example.zad4;

import com.google.gson.Gson;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
        try {
            generateVcard(companies.get(0),response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return companies;
    }

    @PostMapping("/vcard")
    public void generateVcard(@RequestParam(value = "company") Firm firm, HttpServletResponse response) throws IOException {
        File file = new File("vCard.vcf");
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());

        String card = "BEGIN:VCARD\r\n" +
                "VERSION:4.0\r\n" +
                "ORG:" + firm.getName() + "\r\n" +
                "TEL:" + firm.getTelephone() + "\r\n" +
                "ADR:" + firm.getAddress().toString() + "\r\n" +
                "EMAIL:" + firm.getEmail() + "\r\n" +
                "URL:" + firm.getUrl() + "\r\nEND:VCARD";
        fileWriter.write(card);
        fileWriter.close();

    }
}
