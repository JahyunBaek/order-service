package com.example.ordersservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrdersDto implements Serializable {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private Date CreatedAt;

    private String orderId;
}
