package com.org.testdata.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    @Getter @Setter
    public String url;

    @Getter @Setter
    public List<User> users = new ArrayList<User>();

    @Getter @Setter
    public String product;

    @Override
    public String toString() {
        return "TestData{" +
                "url='" + url + '\'' +
                ", users=" + users +
                ", product='" + product + '\'' +
                '}';
    }
}
