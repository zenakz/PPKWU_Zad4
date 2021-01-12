package com.example.zad4;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.util.UriComponentsBuilder;

public class Firm {
    String name;
    String telephone ="";
    String email="";
    String url="";
    Address address;

    @Override public String toString() {
        return "Company{<br>" + "name='" +name + "<br>" + "address=" + address + "<br>phone=" + telephone + "<br>email=" + email + "}<br>";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String toHtml() {
        StringBuilder html = new StringBuilder();
        html.append("<br>").append(name).append("<br>");;
        html.append(telephone).append("<br>");
        html.append("<a href=").append(url).append(">").append(url).append("</a><br>");
        if(address!=null)html.append(address).append("<br>");
        html.append("<a href=mailto:").append(email).append(">").append(email).append("</a><br>");
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/vcard").replaceQueryParam("name", name).replaceQueryParam("address", address).replaceQueryParam("url", url).replaceQueryParam("email", email).replaceQueryParam("telephone", telephone);

        String path = builder.build().encode().toUriString();
        html.append("<a href=\"").append(path).append("\"><button>Generate vcard</button></a>");
        return html.toString();
    }

}
