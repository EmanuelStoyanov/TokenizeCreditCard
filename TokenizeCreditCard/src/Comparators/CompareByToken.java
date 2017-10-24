/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparators;

import java.util.Comparator;
import MainClasses.CreditCard;
import java.util.Arrays;

public class CompareByToken implements Comparator<CreditCard> {

    
    @Override
    public int compare(CreditCard a, CreditCard b) {
        String[] arr = new String[2];
        arr[0] = a.getTokenizedCreditCard();
        arr[1] = b.getTokenizedCreditCard();
        Arrays.sort(arr);
        if(arr[0].equals(a.getTokenizedCreditCard())){
            return -1;
        }
        else return 1;
    }

}




