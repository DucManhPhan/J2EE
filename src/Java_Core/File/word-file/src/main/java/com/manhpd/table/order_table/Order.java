package com.manhpd.table.order_table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String orderDate;

    private String region;

    private String rep;

    private String item;

    private String units;

    private String unitCost;

    private String totalCost;

}
