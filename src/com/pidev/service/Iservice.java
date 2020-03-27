/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;

import java.util.List;

/**
 *
 * @author Mimi
 */
public interface Iservice <T> {
    void insert(T f);
    void delete(int id);
    void update(T f);
    List<T>getAll();
    T getById(T f);
}
