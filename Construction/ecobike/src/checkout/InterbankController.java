package checkout;

import checkout.CreditCard;
import checkout.PaymentTransaction;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import utils.Configs;

public class InterbankController {
    private InterbankBoundary interbankBoundary = new InterbankBoundary();

    public String payRental(CreditCard card, int amount, String contents) throws IOException, JSONException, ParseException {
    	System.out.println("Thanh Toan");
        PaymentTransaction requestTransaction = new PaymentTransaction();
        requestTransaction.setCard(card);
        requestTransaction.setAmount(amount);
        requestTransaction.setTransactionContent(contents);
        requestTransaction.setCreateAt(new Date());
        
        String transactionRequest = getTransactionRequest(requestTransaction, "pay");
        System.out.println(transactionRequest);
        JSONObject response = interbankBoundary.request(transactionRequest);

        return response.getString("errorCode");
    }

    public String refund(CreditCard card, int amount, String contents) throws IOException, JSONException, ParseException {
    	System.out.println("Refund");
        PaymentTransaction requestTransaction = new PaymentTransaction();
        requestTransaction.setCard(card);
        requestTransaction.setAmount(amount);
        requestTransaction.setTransactionContent(contents);
        requestTransaction.setCreateAt(new Date());

        String transactionRequest = getTransactionRequest(requestTransaction, "refund");
        System.out.println(transactionRequest);
        JSONObject response = interbankBoundary.request(transactionRequest);

        return response.getString("errorCode");
    }

    /**
     * Make payment transaction
     * and throw exception base on error code
     * @param response
     * @return
     * @throws PaymentException
     * @throws JSONException
     */

    /**
     * Full transaction request for InterbankBoundary
     * @param paymentTransaction
     * @param command: "pay" or "refund"
     * @return
     * @throws JSONException
     */
    private String getTransactionRequest(PaymentTransaction paymentTransaction, String command) throws JSONException {
        JSONObject transaction = getJSONTransaction(paymentTransaction, command);

        String hashCode = getHashCode(getJsonToHashCode(transaction).toString());
        JSONObject bodyRequest = new JSONObject();

        bodyRequest.put("version", Configs.VERSION);
        bodyRequest.put("transaction", transaction);
        bodyRequest.put("appCode", Configs.APP_CODE);
        bodyRequest.put("hashCode", hashCode);
        return bodyRequest.toString();
    }

    /**
     * Hash code a string using md5 encoder
     * @param jsonString
     * @return
     */
    private String getHashCode(String jsonString){
        return DigestUtils.md5Hex(jsonString);
    }

    /**
     * Add secretKey and create an object to hash
     * @param jsonTransaction
     * @return
     * @throws JSONException
     */
    private JSONObject getJsonToHashCode(JSONObject jsonTransaction) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("secretKey", Configs.SECRET_KEY);
        obj.put("transaction", jsonTransaction);
        return obj;
    }

    /**
     * Put transaction info to a JSONObject
     * @param paymentTransaction
     * @param command
     * @return
     * @throws JSONException
     */
    private JSONObject getJSONTransaction(PaymentTransaction paymentTransaction, String command) throws JSONException {
        JSONObject obj = new JSONObject();
        CreditCard creditCard = paymentTransaction.getCard();
        obj.put("command", command);
        obj.put("cardCode", creditCard.getCardCode());
        obj.put("owner", creditCard.getOwner());
        obj.put("cvvCode", creditCard.getCvvCode());
        obj.put("dateExpired", creditCard.getDateExpired());
        obj.put("transactionContent", paymentTransaction.getTransactionContent());
        obj.put("amount", paymentTransaction.getAmount());
        obj.put("createdAt", format(paymentTransaction.getCreateAt()));
        return obj;
    }

    private String format(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = formatter.format(date);
        System.out.println(strDate);
        return strDate;
    }
}
