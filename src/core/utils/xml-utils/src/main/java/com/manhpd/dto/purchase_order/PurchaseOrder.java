package com.manhpd.dto.purchase_order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;


@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlType(propOrder = {"items", "customer", "comment"})
public class PurchaseOrder {

    private List<Item> items;

    private Customer customer;

    private String comment;

}
