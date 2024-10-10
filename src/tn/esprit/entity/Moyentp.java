/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

/**
 *
 * @author Molka
 */
public class Moyentp {
     private int id;
    private int nbreplace;
    private String matricule,horaire,type,nom;
    private float prix_ticket;

    public Moyentp() {
    }

    
    public Moyentp(String matricule,String type,int nbreplace,float prix_ticket, String horaire,String nom) {
        this.matricule= matricule;
        this.type= type;
        this.nbreplace = nbreplace;
         this.prix_ticket = prix_ticket;
        this.horaire = horaire;
         this.nom = nom;
        
       
    }

    public Moyentp(int aInt, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    

   

    public String getType() {
        return type;
    }

    public String getHoraire() {
        return horaire;
    }

   

    public String getMatricule() {
        return matricule;
    }

    public int getNbreplace() {
        return nbreplace;
    }

    public void setPrix_ticket(float prix_ticket) {
        this.prix_ticket = prix_ticket;
    }

    public float getPrix_ticket() {
        return prix_ticket;
    }

    

    public void setType(String type) {
        this.type = type;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

   
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setNbreplace(int nbreplace) {
        this.nbreplace = nbreplace;
    }

  

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
     

    
    @Override
    public String toString() {
        return "Moyentp{" + "matricule=" + matricule + ", horaire=" + horaire + ", nbreplace=" + nbreplace + ", prix_ticket=" + prix_ticket + ", nom="+ nom +'}';
    }
    
    
}
