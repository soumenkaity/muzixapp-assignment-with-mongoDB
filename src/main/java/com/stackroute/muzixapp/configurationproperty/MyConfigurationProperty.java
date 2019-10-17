package com.stackroute.muzixapp.configurationproperty;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("my-properties")
public class MyConfigurationProperty {
    private int id;
    private String name;
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
