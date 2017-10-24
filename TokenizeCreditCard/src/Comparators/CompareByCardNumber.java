package Comparators;

import java.util.Comparator;
import MainClasses.CreditCard;
import java.util.Arrays;

public class CompareByCardNumber implements Comparator<CreditCard> {

    @Override
    public int compare(CreditCard a, CreditCard b) {
        String[] arr = new String[2];
        arr[0] = a.getCreditCardNumber();
        arr[1] = b.getCreditCardNumber();
        Arrays.sort(arr);
        if(arr[0].equals(a.getCreditCardNumber())){
            return -1;
        }
        else return 1;
    }

}

