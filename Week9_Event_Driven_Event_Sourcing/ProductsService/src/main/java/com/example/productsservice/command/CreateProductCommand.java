package com.example.productsservice.command;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateProductCommand {
    @TargetAggregateIdentifier
    private final String productId;
    private final String title;
    private final BigDecimal price;
    private final Integer quatity;
}
