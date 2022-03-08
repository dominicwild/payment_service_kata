package com.payment_service_kata;

public class PaymentService {

    private UserRepository userRepository;
    private PaymentGateway paymentGateWay;

    public PaymentService(UserRepository userRepository, PaymentGateway paymentGateWay) {
        this.userRepository = userRepository;
        this.paymentGateWay = paymentGateWay;
    }

    public void processPayment(User user, PaymentDetails paymentDetails) {
        userRepository.getUser(user).ifPresentOrElse(dbUser -> paymentGateWay.sendPaymentDetails(paymentDetails),
                () -> {
                    throw new IllegalArgumentException("User does not exist");
                });
    }

}
