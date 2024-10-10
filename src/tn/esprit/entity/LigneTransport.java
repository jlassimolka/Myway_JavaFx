/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

/**
 *
 * @author Admin
 */
public class LigneTransport {
    private int id;
    private Moyentp moyenTransport;
    private Trajet trajet;
    

    public LigneTransport() {
    }

    public LigneTransport(Moyentp moyenTransport, Trajet trajet) {
        this.moyenTransport = moyenTransport;
        this.trajet = trajet;
    }

    public int getId() {
        return id;
    }

    public Moyentp getMoyenTransport() {
        return moyenTransport;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMoyenTransport(Moyentp moyenTransport) {
        this.moyenTransport = moyenTransport;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    @Override
    public String toString() {
        return "LigneTransport{" + "id=" + id + ", moyenTransport=" + moyenTransport + ", trajet=" + trajet + '}';
    }
    
}
