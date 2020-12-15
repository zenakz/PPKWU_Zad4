package com.example.zad4;

import java.net.URL;

public class Firm {
    String name;
    String image;
    String telephone;
    String email;
    String sameas;
    String url;
    Address address;

    @Override public String toString() {
        return "Company{<br>" + "name='" +name + "<br>" + "address=" + address + "<br>phone=" + telephone + "<br>email=" + email + "}<br>";
    }
}
