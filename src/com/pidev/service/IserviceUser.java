/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;

import javafx.collections.ObservableList;

/**
 *
 * @author Damdoub
 */
public interface IserviceUser {
    public interface IService<T> {
    void insert(T t);
    void delete(T t);
    void update(T t);
    T searchByMail(T t);
    ObservableList<T>getAll();
    ObservableList<T>getUsers();
    T getById(T t);
    
}

    
}
