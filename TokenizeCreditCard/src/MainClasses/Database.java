/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import StaticMethods.Validation;
import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

public class Database implements Serializable {

    private final File xmlFile = new File("Users.xml");
    private final XStream xstream = new XStream();
    private List<User> users = new LinkedList<>();

    public Database() throws IOException {

        xstream.alias("User", User.class);
        xstream.alias("CreditCard", CreditCard.class);

        if (!xmlFile.exists()) {
            toXML();
        }
    }
    
    private void toXML() throws IOException {
        xstream.toXML(users, new FileWriter(xmlFile));
    }

    public List<User> fromXML() {
        return (List<User>) xstream.fromXML(xmlFile);
    }

    public void add(User userToAdd) throws IOException{

        users = fromXML();
        boolean isRegistered = false;
        
        for (User regUser : users) {
            if (regUser.getUsername().equals(userToAdd.getUsername())) {
                JOptionPane.showMessageDialog(null, "Already registered user.");
                isRegistered = true;
            }
        }
        
        if(!isRegistered){
            if(!Validation.validateUsername(userToAdd.getUsername())){
                JOptionPane.showMessageDialog(null, "Invalid Username."
                        + "It should be only small letters and "
                        + "numbers!");
            }
            else if(!Validation.validatePassword(userToAdd.getPassword())){
                JOptionPane.showMessageDialog(null, "Invalid Password."
                        + "It must include a big letter, a small letter "
                        + "and a number");
            }
            else{
                users.add(userToAdd);
                toXML();
            }
        }
        
    }
    
    public void update(User user) throws IOException {
        users = fromXML();
        
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername())) {
                users.set(i, new User(user));
                toXML();
            }
        }
    }
    
    public User getUserByName(String name) {
        users = fromXML();
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return new User(user);
            }
        }
        return null;
    }

}
