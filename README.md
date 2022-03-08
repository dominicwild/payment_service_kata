# Payment Service Kata

Problem description:  Payment service
 		 
Given a user wants to buy her selected items
When she submits her payment details
Then we should process her payment

Acceptance criteria:
- If the user does not exist, an exception should be thrown. 
- Payment should be sent to the payment gateway.

Create a class with the following signature:

```
public class PaymentService {
	public void processPayment(User user, 
                              PaymentDetails paymentDetails) {
		// your code goes here
	}
}
```
