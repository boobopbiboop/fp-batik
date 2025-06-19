package com.example.fp_batik;

public class BatikItem {
    private String id;
    private String name;
    private String origin;
    private String imageUrl;
    private Integer era;
    private String type;
    private String meaning;
    private String philosophy;
    private String history;
    private String imageUrl;  // Changed from mainImageUrl to match constructor
    private String[] variationImages;

    public BatikItem(String id, String name, String origin, String imageUrl, Integer era, String meaning) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.era = era;
        this.type = type;
        this.meaning = meaning;
    }

    // Constructor with type
    public BatikItem(String id, String name, String origin, String imageUrl, String type, Integer era, String meaning) {
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

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Integer getEra() { return era; }
    public void setEra(Integer era) { this.era = era; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getMeaning() { return meaning; }
    public void setMeaning(String meaning) { this.meaning = meaning; }
}