package com.professional.MyEcommerce.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.professional.MyEcommerce.dto.Purchase;
import com.professional.MyEcommerce.dto.PurchaseResponse;
import com.professional.MyEcommerce.services.CheckoutService;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
	
	private CheckoutService checkoutService;

	protected CheckoutController(CheckoutService checkoutService) {
		
		this.checkoutService = checkoutService;
	}
	
    @PostMapping("/purchase")
	public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
    	PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
    	
    	return purchaseResponse;
    }
	
	

}
