package com.saloon.service;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.saloon.domain.PaymentMethod;
import com.saloon.model.PaymentOrder;
import com.saloon.payload.BookingDTO;
import com.saloon.payload.UserDTO;
import com.saloon.payload.response.PaymentLinkResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {

    PaymentLinkResponse createOrder(UserDTO user, BookingDTO booking, PaymentMethod paymentMethod ) throws Exception;

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    PaymentOrder getPaymentOrderByPaymentId(String paymentId);

    PaymentLink createRazorpayPaymentLink(UserDTO user, Long amount, Long orderId) throws RazorpayException;

    String createStripePaymentLink(UserDTO user, Long amount, Long orderId) throws StripeException;

    Boolean proceedPayment(PaymentOrder paymentOrder, String paymentId, String paymentLinkId);
}
