/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import com.pidev.entity.Utilisateur;

public  class UserSession {

    private static UserSession instance;



    private Utilisateur user =new Utilisateur();
    

    private UserSession(Utilisateur user) {
        this.user=user;
    
    }

    public static UserSession getInstace(Utilisateur user) {
      
        instance = new UserSession(user);
        
        return instance;
    }
    public static UserSession getInstace() {
        
        return instance;
    }

    public Utilisateur getUser() {
        return user;
    }

  

    public void cleanUserSession() {
        user=null;
    }

    @Override
    public String toString() {
        return "User" + user +"}";
    }
}