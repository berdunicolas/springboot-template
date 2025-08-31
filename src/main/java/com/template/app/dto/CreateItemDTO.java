package com.template.app.dto;

import com.template.app.model.Item;
import com.template.app.validation.Unique;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateItemDTO {

    @Unique(
        table = "items",
        column = "name"
    )
    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String name;

    @Min(value = 0, message = "El precio debe ser un valor positivo")
    private Double price;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }


    public Item makeItem(){
        return new Item(name, price);
    }
}
