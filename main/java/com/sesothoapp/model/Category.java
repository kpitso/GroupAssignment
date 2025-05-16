package com.sesothoapp.model;

/*
 * Represents a category of questions in the Sesotho Learning App.
 */
public class Category {
    private final String id;
    private final String name;
    private final String iconName;
    private final String description;


     // Constructs a new Category.
    public Category(String id, String name, String iconName, String description) {
        this.id = id;
        this.name = name;
        this.iconName = iconName;
        this.description = description;
    }

     // Get the category ID
    public String getId() {
        return id;
    }

     // Get the category name.
    public String getName() {
        return name;
    }

     //Get the icon name.
    public String getIconName() {
        return iconName;
    }

     // Get the description
    public String getDescription() {
        return description;
    }
}