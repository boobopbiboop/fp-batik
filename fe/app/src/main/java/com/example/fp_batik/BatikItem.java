package com.example.fp_batik;

public class BatikItem {
    private String id;
    private String name;
    private String origin;
    private String imageUrl;
    private String era;
    private String type;
    private String meaning;

    public BatikItem(String id, String name, String origin, String imageUrl, String era, String meaning) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.imageUrl = imageUrl;
        this.era = era;
        this.meaning = meaning;
    }

    // Constructor with type
    public BatikItem(String id, String name, String origin, String imageUrl, String type, String era, String meaning) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.imageUrl = imageUrl;
        this.type = type;
        this.era = era;
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

    public String getEra() { return era; }
    public void setEra(String era) { this.era = era; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getMeaning() { return meaning; }
    public void setMeaning(String meaning) { this.meaning = meaning; }
}
