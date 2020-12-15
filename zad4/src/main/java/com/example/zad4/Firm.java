package com.example.zad4;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Firm {
    String name;
    String telephone;
    String email;
    String url;
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
}
