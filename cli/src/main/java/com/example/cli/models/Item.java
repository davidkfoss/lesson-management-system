package com.example.cli.models;

public class Item {
    private Long id;
    private String name;
    private String description;

    // Getters and setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString() method for easy display
    @Override
    public String toString() {
        return "Item{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}
