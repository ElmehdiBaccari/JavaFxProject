/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;

import java.util.List;
import javafx.scene.layout.AnchorPane;

/**
 *
 * 
 * @param <T>
 */
public interface IServicelivre<T> {
    
    void ajouterlivre(T t);
    
    void delete(T t);
    void update(T t);
    List<T>getAll();
    void emprunter_livre(T t);
    List<AnchorPane>getLivreContainer(String s);
    
    
    T getById(T t);
    
}