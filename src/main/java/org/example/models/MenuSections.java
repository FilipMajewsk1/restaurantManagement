package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MenuSections {
    @JsonProperty("Appetizer")
    Appetizer,
    @JsonProperty("Soup")
    Soup,
    @JsonProperty("MainCoarse")
    MainCoarse,
    @JsonProperty("Dessert")
    Dessert
}
