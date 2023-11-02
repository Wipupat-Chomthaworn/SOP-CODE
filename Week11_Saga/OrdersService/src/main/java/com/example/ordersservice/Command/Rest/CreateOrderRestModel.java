package com.example.ordersservice.Command.Rest;

import com.example.ordersservice.OrderStatus;
import lombok.Data;

@Data
public class CreateOrderRestModel {

    private String productId;
    private int quantity;
    private String addressId;
//    each attr come form this สำหรับสราง order ใหม โดยจะตองรับ JSON payload แบบนี้ {
//        "productId": "f241af45-4854-43f4-95bc-ab54da338a29",
//                "quantity": 1,
//                "addressId": "afbb5881-a872-4d13-993c-faeb8350eea5"
//    }
}
