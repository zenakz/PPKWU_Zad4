package com.example.zad4;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Firm {
    String name;
    String image;
    String telephone;
    String email;
    @JsonIgnore
    String sameas;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getSameas() {
        return sameas;
    }

    public void setSameas(String sameas) {
        this.sameas = sameas;
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
