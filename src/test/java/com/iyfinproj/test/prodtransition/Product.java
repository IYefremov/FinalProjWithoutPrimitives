package com.iyfinproj.test.prodtransition;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
        private final String title;
        private final double oldPrice;
        private final double newPrice;
        private final String name;
}
