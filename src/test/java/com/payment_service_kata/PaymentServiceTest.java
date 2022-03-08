package com.payment_service_kata;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    User user;

    @Mock
    PaymentDetails paymentDetails;
    
    @Mock
    PaymentGateway paymentGateWay;

    @InjectMocks
    PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentService(userRepository, paymentGateWay);
    }

    @Test
    void processing_payment_for_user_that_does_not_exist_throws_exception() {
        when(userRepository.getUser(user)).thenReturn(Optional.empty());
        
        assertThrows(IllegalArgumentException.class, () -> paymentService.processPayment(user, paymentDetails));
    }

    @Test
    void processing_payment_for_non_existing_user_does_not_send_payment_details_to_gateway(){
        when(userRepository.getUser(user)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> paymentService.processPayment(user, paymentDetails));

        verify(paymentGateWay, never()).sendPaymentDetails(paymentDetails);
    }

    @Test
    void processing_payment_for_user_sends_payment_details_to_gateway() {
        when(userRepository.getUser(user)).thenReturn(Optional.of(user));

        paymentService.processPayment(user, paymentDetails);

        verify(paymentGateWay).sendPaymentDetails(paymentDetails);
    }

}
