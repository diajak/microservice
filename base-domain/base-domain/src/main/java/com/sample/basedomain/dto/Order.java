package com.sample.basedomain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String orderId;
    private String ordername;
    private int qty;
    private double price;
}
