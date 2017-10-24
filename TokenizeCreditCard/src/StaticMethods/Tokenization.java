/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StaticMethods;

import java.util.Random;

/**
 *
 * @author emo
 */
public class Tokenization {
    
    private static Random random = new Random();
    
    public static String tokenize(String creditCardNumber){
        
        char[] toDigits = creditCardNumber.toCharArray();
        String token = "";
        int sum=0;
        
        while(sum % 10 == 0){
            sum=0;
            
            int randomNumber = random.nextInt(10);
            while(randomNumber>=3 && randomNumber<=6){
                randomNumber = random.nextInt(10);
            }
            sum+=randomNumber;
            token+=randomNumber;

            for(int i=1; i<12; i++){
                int intValue = Character.getNumericValue(toDigits[i]);
                randomNumber = intValue;
                while(randomNumber == intValue){
                    randomNumber = random.nextInt(10);
                }
                sum+=randomNumber;
                token+=randomNumber;
            }

            for(int i=12; i<16; i++){
                int intValue = Character.getNumericValue(toDigits[i]);
                sum+=intValue;
                token+=intValue;
            }
        }
        if(token.length() == 16){
            return token;
        }
        else{
            return tokenize(creditCardNumber);
        }
    }
}
