/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import java.io.Serializable;

/**
 *
 * @author emo
 */
public class CreditCard implements Serializable{
    private String creditCardNumber;
    private String tokenizedCreditCard;

    public CreditCard(String creditCardNumber, String tokenizedCreditCard) {
        this.creditCardNumber = creditCardNumber;
        this.tokenizedCreditCard = tokenizedCreditCard;
    }
    
    public CreditCard(){
        creditCardNumber = "1";
        tokenizedCreditCard = "1";
    }
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getTokenizedCreditCard() {
        return tokenizedCreditCard;
    }
    public String toString(){
        return "Credit card:" + creditCardNumber + " Token" + tokenizedCreditCard;
    }
}
