package kz.medet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Customer {
    private Long id;
    private String firstName;
    private String lastname;
    @Builder.Default
    private List<Order> orders = new ArrayList<>();
}
