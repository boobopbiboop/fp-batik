package com.example.fp_batik;

public class BatikItem {
    private String id;
    private String name;
    private String origin;
    private String era;
    private String type;
    private String meaning;
    private String philosophy;
    private String history;
    private String imageUrl;  // Changed from mainImageUrl to match constructor
    private String[] variationImages;

    // Fixed constructor with proper braces and parameter assignment
    public BatikItem(String id, String name, String origin, String imageUrl, String type, String meaning,
                     String era, String philosophy, String history, String[] variationImages) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.era = era;
        this.type = type;
        this.meaning = meaning;
        this.philosophy = philosophy;
        this.history = history;
        this.imageUrl = imageUrl;  // Fixed assignment
        this.variationImages = variationImages;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getType() {
        return type;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getEra() {
        return era;
    }

    public String getPhilosophy() {
        return philosophy;
    }

    public String getHistory() {
        return history;
    }

    public String[] getVariationImages() {
        return variationImages;
    }

    // Setters (optional)
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setEra(String era) {
        this.era = era;
    }

    public void setPhilosophy(String philosophy) {
        this.philosophy = philosophy;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public void setVariationImages(String[] variationImages) {
        this.variationImages = variationImages;
    }
}