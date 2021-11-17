package com.professional.MyEcommerce.dto;

import java.util.Set;

import com.professional.MyEcommerce.entity.Address;
import com.professional.MyEcommerce.entity.Customer;
import com.professional.MyEcommerce.entity.Order;
import com.professional.MyEcommerce.entity.OrderItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Purchase {

	private Customer customer;
	private Address shippingAddress;
	private Address billingAddress;
	private Order order;
	private Set<OrderItem> orderItems;

}
