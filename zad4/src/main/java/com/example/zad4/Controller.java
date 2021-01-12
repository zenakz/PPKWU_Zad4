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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @GetMapping(value="/search")
    String getSearchResults(HttpServletRequest request, HttpServletResponse response) {
        String url = "https://panoramafirm.pl/szukaj?k="+"d";
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
        StringBuilder html = new StringBuilder();
        for (Firm firm : companies) {
            html.append(firm.toHtml()).append("<br>");
        }
        return html.toString();
    }

    @GetMapping("/card")
    public void generateVcard(@RequestParam(name = "name") String name,
                              @RequestParam(name = "address") String address,
                              @RequestParam(name = "url") String url,
                              @RequestParam(name = "email") String email,
                              @RequestParam(name = "telephone") String telephone,HttpServletResponse response) throws IOException {
        File file = new File("vCard.vcf");
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());

        String card = "BEGIN:VCARD\r\n" +
                "VERSION:4.0\r\n" +
                "ORG:" + name + "\r\n" +
                "TEL:" + telephone + "\r\n" +
                "EMAIL:" + email + "\r\n" +
                "ADR:" + address + "\r\n" +
                "URL:" + url + "\r\nEND:VCARD";
        fileWriter.write(card);
        fileWriter.close();

        InputStream inputStream = new FileInputStream(file);
        response.setContentType("text/vcard;charset=utf-8");
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }
}
