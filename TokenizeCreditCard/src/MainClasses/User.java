/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author emo
 */
public class User implements Serializable{
    private String username;
    private String password;
    private boolean canRegister = true;
    private boolean canReadInformation = true;
    private ArrayList<CreditCard> list = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public User(){
        username = "defaultUsername";
        password = "defaultPassword1";
    }
    
    public User(User user) {
        this(user.getUsername(), user.getPassword());
        list.addAll(user.getList());
    }

    public void setCanRegister(boolean canRegister) {
        this.canRegister = canRegister;
    }

    public void setCanReadInformation(boolean canReadInformation) {
        this.canReadInformation = canReadInformation;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<CreditCard> getList() {
        return list;
    }
    

    public boolean canRegister() {
        return canRegister;
    }

    public boolean canReadInformation() {
        return canReadInformation;
    }
    
    public String toString(){
        return String.format("Username: %s Password: %s", username, password);
    }
    
    public void addCreditCard(CreditCard creditCard) {
        list.add(creditCard);
    }
    
    public boolean isThereToken(String token){
        for(CreditCard creditCard : list){
            if(creditCard.getTokenizedCreditCard().equals(token)){
                return true;
            }
        }
        return false;
    }
    
    public CreditCard getByToken(String token){
        for(CreditCard creditCard : list){
            if(creditCard.getTokenizedCreditCard().equals(token)){
                return creditCard;
            }
        }
        return null;
    }
}
