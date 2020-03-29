package company.tap.ibtikarat.interfaces;


import company.tap.ibtikarat.callbacks.GoSellError;
import company.tap.ibtikarat.models.Charge;

/**
 * The interface Payment process listener.
 */
public interface IPaymentProcessListener {
    /**
     * Did receive charge.
     *
     * @param charge the charge
     */
    void didReceiveCharge(Charge charge);



    /**
     * Did receive error.
     *
     * @param error the error
     */
    void didReceiveError(GoSellError error);


}