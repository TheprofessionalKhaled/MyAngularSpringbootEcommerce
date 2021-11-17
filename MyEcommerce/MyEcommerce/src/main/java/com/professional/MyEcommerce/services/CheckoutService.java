package com.professional.MyEcommerce.services;

import com.professional.MyEcommerce.dto.Purchase;
import com.professional.MyEcommerce.dto.PurchaseResponse;

public interface CheckoutService {
	PurchaseResponse placeOrder(Purchase purchase);

}
