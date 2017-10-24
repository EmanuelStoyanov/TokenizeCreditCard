/*
 * To change this license head, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import MainClasses.Database;
import MainClasses.CreditCard;
import MainClasses.User;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;

public final class Session implements Serializable {

    private User user;
    transient private Database db = new Database();

    public Session(User user) throws IOException {
        if (user != null) {
            logIn(user);
        }
    }

    public void logIn(User userForLogging) {

        User registredUser = db.getUserByName(userForLogging.getUsername());
        if(registredUser == null){
            JOptionPane.showMessageDialog(null, "There is no such registred user.");
        }
        else if (registredUser.getPassword().equals(userForLogging.getPassword())) {
            this.user = new User(registredUser);
        } else {
            JOptionPane.showMessageDialog(null, "Wrong password.");
        }
    }

    public Database registerCard(CreditCard card, Database db) throws IOException {

        this.db = db;
        if (user.canRegister()) {
            user.addCreditCard(card);
            db.update(user);
            return db;
        }
        else{
            JOptionPane.showMessageDialog(null, "This user do not have rights to register.");
            return null;
        }
           
    }

    public void exportByComparator(Comparator comparator, File file) throws IOException{
        if (!user.canReadInformation()) {
            JOptionPane.showMessageDialog(null, "This user do not have rights to read.");
        }
        
        ArrayList<CreditCard> list = new ArrayList<>();
        list.addAll(user.getList());
        Collections.sort(list,comparator);

        String head = String.format("%-18s<->%-18s\n", "Credit Card:", "Token:");
        String row;
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(head);

        for (CreditCard creditCard : list ) {
            row = String.format("%-18s<->%-18s\n", 
                                     creditCard.getCreditCardNumber(), 
                                     creditCard.getTokenizedCreditCard());
            System.out.print(row);
            bw.write(row);
        }

        bw.close(); 

    }

    public String getCardByToken(String token){
        if (user.canReadInformation()) {
            CreditCard card = user.getByToken(token);
            if (card == null) {
                JOptionPane.showMessageDialog(null, "There is no such token registered.");
                return null;
            } else {
                return card.getCreditCardNumber();
            }
        } else {
            JOptionPane.showMessageDialog(null, "This user do not have the rights to read.");
            return null;
        }
    }
    
    public User getUser() {
        return user;
    }
 
    @Override
    public String toString(){
        return "Session:" + user;
    }
}
