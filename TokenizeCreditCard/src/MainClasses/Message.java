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

public class Message implements Serializable{

    private final Object obj;
    private final String action;

    public Message(Object obj, String action) {
        this.obj = obj;
        this.action = action;
    }

    public Object getObject() {
        return obj;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "Message{" + "obj=" + obj + ", action=" + action + '}';
    }

}
