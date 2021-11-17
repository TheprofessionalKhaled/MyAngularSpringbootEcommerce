package com.professional.MyEcommerce.services;

import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.professional.MyEcommerce.dao.CustomerRepository;
import com.professional.MyEcommerce.dto.Purchase;
import com.professional.MyEcommerce.dto.PurchaseResponse;
import com.professional.MyEcommerce.entity.Customer;
import com.professional.MyEcommerce.entity.Order;
import com.professional.MyEcommerce.entity.OrderItem;


public  class CheckoutServiceImp implements CheckoutService{
	
	private CustomerRepository customerRepository;
	
	

	public CheckoutServiceImp(CustomerRepository customerRepository) {
	
		this.customerRepository = customerRepository;
	}



	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		// TODO Auto-generated method stub
		Order order = purchase.getOrder();
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item->order.add(item));
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());
		
		Customer customer = purchase.getCustomer();
		String theEmail = customer.getEmail();
		
		Customer customerFromDB = customerRepository.findByEmail(theEmail);
		
		if(customerFromDB != null) {
			customer = customerFromDB;
		}
		
		customer.add(order);
		customerRepository.save(customer);
		
		return new PurchaseResponse(orderTrackingNumber );
		
			
		
	}



	private String generateOrderTrackingNumber() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString();
	}
	
}
