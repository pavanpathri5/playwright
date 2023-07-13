package com.org.testdata.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class APIData {
    private Demoqa demoqa;
    public Demoqa getDemoqa() {
        return demoqa;
    }
    public void setDemoqa(Demoqa demoqa) {
        this.demoqa = demoqa;
    }
}

