package com.example.audiobook.entity;


import java.sql.Time;
import java.time.LocalDate;

public class Book {
    private Long id;
    private String name;
    private String cover_image;
    private String audio_file;
    private Time duration;
    private String language;
    private String description;
    private User author;
    private User narrator;
    private User publisher;
    private LocalDate publication_year;
    private float avg_rating;
    private float ratings_count;
    private LocalDate created_at;
    private LocalDate updated_at;

    public Book() {
    }

    public Book(Long id, String name, String cover_image, String audio_file, Time duration, String language, String description, User author, User narrator, User publisher, LocalDate publication_year, float avg_rating, float ratings_count, LocalDate created_at, LocalDate updated_at) {
        this.id = id;
        this.name = name;
        this.cover_image = cover_image;
        this.audio_file = audio_file;
        this.duration = duration;
        this.language = language;
        this.description = description;
        this.author = author;
        this.narrator = narrator;
        this.publisher = publisher;
        this.publication_year = publication_year;
        this.avg_rating = avg_rating;
        this.ratings_count = ratings_count;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

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

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getAudio_file() {
        return audio_file;
    }

    public void setAudio_file(String audio_file) {
        this.audio_file = audio_file;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getNarrator() {
        return narrator;
    }

    public void setNarrator(User narrator) {
        this.narrator = narrator;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(LocalDate publication_year) {
        this.publication_year = publication_year;
    }

    public float getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(float avg_rating) {
        this.avg_rating = avg_rating;
    }

    public float getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(float ratings_count) {
        this.ratings_count = ratings_count;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cover_image='" + cover_image + '\'' +
                ", audio_file='" + audio_file + '\'' +
                ", duration=" + duration +
                ", language='" + language + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", narrator=" + narrator +
                ", publisher=" + publisher +
                ", publication_year=" + publication_year +
                ", avg_rating=" + avg_rating +
                ", ratings_count=" + ratings_count +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
