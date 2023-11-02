package com.example.ordersservice.Data;

import com.example.ordersservice.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name ="orders")
@Data
public class OrderEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 7102871651334248691L;

    @Id
    @Column(unique = true)
    public String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @jakarta.persistence.Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
