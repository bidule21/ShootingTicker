/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janbrodda.shootingticker.client.gui;

/**
 *
 * @author broddaja
 * @param <E> The Value Class
 */
public class ComboboxItem<E> {
    public String name;
    public E value;
    
    public ComboboxItem(String name, E value){
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }
}
