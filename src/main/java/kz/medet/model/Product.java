package kz.medet.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Long id;
    private String name;
    private double price;
}
