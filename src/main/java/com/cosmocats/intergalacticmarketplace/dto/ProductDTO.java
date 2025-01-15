package com.cosmocats.intergalacticmarketplace.dto;

import javax.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class ProductDTO {

    @NotNull(message = "Name cannot be null")
    private String name;

    @Positive(message = "Price must be greater than 0")
    private double price;

}

