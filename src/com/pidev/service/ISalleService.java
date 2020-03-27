/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;

import com.pidev.entity.Utilisateur;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Damdoub
 */
public interface ISalleService {
    public interface ISalle<T> {
    void insert(T t);
    void delete(T t);
    void update(T t);
    T searchByName(T t);
    ObservableList<T>getAll();
    List<AnchorPane>getSalleContainer(String s);
    ObservableList<T>getSalles();
    T getById(T t);
    void Demander(T t,Utilisateur u,LocalDate l,String besoin);
    
}
    
}
