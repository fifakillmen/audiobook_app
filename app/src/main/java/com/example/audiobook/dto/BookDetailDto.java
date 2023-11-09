package com.example.audiobook.dto;

import java.sql.Time;

public class BookDetailDto {
    private Long id;

    private String name;

    private String author;
    private String cover_image;

    private Time duration;

    private String language;
    private String description;

    private float avg_rating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(float avg_rating) {
        this.avg_rating = avg_rating;
    }

    public BookDetailDto(Long id, String name, String author, String cover_image, Time duration, String language, String description, float avg_rating) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.cover_image = cover_image;
        this.duration = duration;
        this.language = language;
        this.description = description;
        this.avg_rating = avg_rating;
    }
}


