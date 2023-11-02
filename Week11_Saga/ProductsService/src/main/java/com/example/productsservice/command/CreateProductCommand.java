package com.example.productsservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Builder
@Data
public class CreateProductCommand {
    @TargetAggregateIdentifier
    private final String productId; //productId เป็นแนวprimary key บอกว่า aggregate อะมีป่าวไม่มีก็สร้าง
    private final String title;
    private final BigDecimal price;
    private final Integer quantity; // final จะได้ไม่มีใครเปลี่ยน command เราได้ รวมถึง event ด้วย
}
