package com.example.OnlineFoodOrdering.Service;

import com.example.OnlineFoodOrdering.model.Order;
import com.example.OnlineFoodOrdering.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {
    public PaymentResponse createPaymentLink(Order order) throws StripeException;

}
