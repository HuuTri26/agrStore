package agrStore.paypal;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

import agrStore.entity.AccountEntity;
import agrStore.entity.OrderBillDetailEntity;
import agrStore.entity.OrderBillEntity;

@Service
public interface PayPalPaymentService {
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
	public Payment getPaymentDetails(String paymentId) throws PayPalRESTException;
	public String authorizePayment( AccountEntity payer, List<OrderBillDetailEntity> orderItems) throws PayPalRESTException;
	public String getApprovalLink(Payment approvedPayment);
	public String convertNumberFromGermanToUSFormat(String germanNum);
	public List<Transaction> getTransactionInfomation(List<OrderBillDetailEntity> orderItems);
	public RedirectUrls getRedirectUrls();
	public Payer getPayerInfomation(AccountEntity payerAccount);
}
