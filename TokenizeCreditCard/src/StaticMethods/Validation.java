/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StaticMethods;

/**
 *
 * @author emo
 */
public class Validation {
    public static boolean validateCreditCard(String creditCardNumber){
        return creditCardNumber.matches("^[3-6][0-9]{15}$") && isLuhn(creditCardNumber);
    }
    public static boolean validateUsername(String username){
        return username.matches("^[a-z0-9_-]{3,15}$");
    }
    public static boolean validatePassword(String password){
        return password.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})");
    }
    
    public static boolean isLuhn(String creditCardNumber){
        char[] toDigits = creditCardNumber.toCharArray();
        int sum = 0;
        
        for(int i = toDigits.length-1; i>=0; i--){
            int number = Character.getNumericValue(toDigits[i]);
            if(i%2 == 0)
            {
                number*=2;
                if(number>9){
                    int first = number/10;
                    int second = number%10;
                    number = first + second;
                }
            }
            sum+=number;
        }
        return sum % 10 == 0;
    }
}
