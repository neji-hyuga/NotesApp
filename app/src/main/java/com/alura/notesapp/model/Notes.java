package com.alura.notesapp.model;

import java.io.Serializable;

public class Notes implements Serializable {

    private final String title;
    private final String description;

    public Notes(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
