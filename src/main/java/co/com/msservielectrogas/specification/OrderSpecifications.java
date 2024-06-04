package co.com.msservielectrogas.specification;

import co.com.msservielectrogas.entity.Order;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecifications {

    public static Specification<Order> hasObservations(String observations) {
        return (root, query, builder) -> observations == null ? null : builder.like(root.get("observations"), "%" + observations + "%");
    }
}