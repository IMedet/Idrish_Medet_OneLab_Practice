package kz.medet.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Order {
    private Long id;
    private Timestamp timeCreated;
    @Builder.Default
    private List<Product> products = new ArrayList<>();
}
