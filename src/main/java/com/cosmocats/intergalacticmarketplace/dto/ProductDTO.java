package com.cosmocats.intergalacticmarketplace.dto;

import com.cosmocats.intergalacticmarketplace.service.mapper.wordcheck.CosmicWordCheck;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductDTO {

    @NotNull(message = "ID не може бути null")
    private Long id;

    @NotNull(message = "Назва не може бути пустою")
    @Size(min = 3, max = 100, message = "Назва повинна бути від 3 до 100 символів")
    @CosmicWordCheck(message = "Назва повинна містити космічні терміни (наприклад, star, galaxy, comet)")
    private String name;

    @Size(max = 500, message = "Опис не може перевищувати 500 символів")
    private String description;

    @NotNull(message = "Ціна не може бути null")
    private Double price;

    // Геттер та сеттер
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
