/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.util.List;

/**
 *
 * @author Molka
 * @param <T>
 */
public interface InterfaceService <T> {
    public void ajouter(T t);
    public List<T> getAll();
    
    public List<T> findByMatricule(String matricule);
    public List<T> findById(int id);
    public void supprimer(T t);
    
}
