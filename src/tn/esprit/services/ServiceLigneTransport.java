/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entity.LigneTransport;
import tn.esprit.entity.Moyentp;
import tn.esprit.entity.Trajet;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author Admin
 */
public class ServiceLigneTransport implements InterfaceService<LigneTransport> {

   Connection cnx;

    public ServiceLigneTransport () {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(LigneTransport t) {
        try {
            String sql;
            sql = "insert into ligne_transport (id_moyentp,id_trajet) values ('"+ t.getMoyenTransport().getId()+ "','"+t.getTrajet().getId()+"')";
            Statement ste = cnx.createStatement();
            
           
            ste.executeUpdate(sql);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<LigneTransport> getAll() {
        List <LigneTransport> list =new ArrayList<>() ;
        
        try {
            String qry = "SELECT l.id, t.id, t.depart, t.destination, t.etat, t.directions, t.image, m.id, m.matricule, m.type, m.nbreplace, m.prix_ticket, m.horaire ,m.nom FROM moyentp m JOIN ligne_transport l ON m.id = l.id_moyentp JOIN trajet t ON l.id_trajet = t.id";
            
         
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                LigneTransport ligneTransport = new LigneTransport();
                Moyentp e = new Moyentp();
                e.setId(rs.getInt("id"));
                e.setMatricule(rs.getString("matricule"));
               
                e.setType(rs.getString("type"));
                
                e.setNbreplace(rs.getInt("nbreplace"));
                e.setPrix_ticket(rs.getFloat("prix_ticket"));
                e.setHoraire(rs.getString("horaire"));
                e.setNom(rs.getString("nom"));
                
                Trajet t = new Trajet();
                t.setId(rs.getInt("id"));
                t.setDepart(rs.getString("depart"));
                t.setDestination(rs.getString("destination"));
                t.setEtat(rs.getString("etat"));
                t.setDirections(rs.getString("directions"));
                t.setImage(rs.getString("image"));

                ligneTransport.setId(rs.getInt("id"));
                ligneTransport.setMoyenTransport(e);
                ligneTransport.setTrajet(t);
                
                
                list.add(ligneTransport);
                
            }
            return list;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
return list;
    }

    @Override
    public List<LigneTransport> findByMatricule(String matricule) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LigneTransport> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(LigneTransport t) {
       try {
            String sql="delete from ligne_transport where id=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
    }
    

     
  public LigneTransport findByIdTrajetAndMatriculeMoyenTp(int idTrajet, int idMoyenTp) {
        LigneTransport ligneTransport = new LigneTransport();
        try {
            String qry = "SELECT l.id, t.id, t.depart, t.destination, t.etat, t.directions, t.image, m.id, m.matricule, m.type, m.nbreplace, m.prix_ticket, m.horaire ,m.nom FROM moyentp m JOIN ligne_transport l ON m.id = l.id_moyentp JOIN trajet t ON l.id_trajet = t.id WHERE id_trajet = " + idTrajet + " AND id_moyentp = " + idMoyenTp  ;
            
         
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                
                Moyentp e = new Moyentp();
                e.setId(rs.getInt("id"));
                e.setMatricule(rs.getString("matricule"));
               
                e.setType(rs.getString("type"));
                
                e.setNbreplace(rs.getInt("nbreplace"));
                e.setPrix_ticket(rs.getFloat("prix_ticket"));
                e.setHoraire(rs.getString("horaire"));
                e.setHoraire(rs.getString("nom"));
                
                Trajet t = new Trajet();
                t.setId(rs.getInt("id"));
                t.setDepart(rs.getString("depart"));
                t.setDestination(rs.getString("destination"));
                t.setEtat(rs.getString("etat"));
                t.setDirections(rs.getString("directions"));
                t.setImage(rs.getString("image"));

                ligneTransport.setId(rs.getInt("id"));
                ligneTransport.setMoyenTransport(e);
                ligneTransport.setTrajet(t);
            }
            return ligneTransport;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ligneTransport;

    }
    
 public void modifier(LigneTransport p) {
       try {
            
            String qry = "UPDATE ligne SET " + "id_moyentp = '" + p.getMoyenTransport().getId() + "'" + ", id_trajet = '" + p.getTrajet().getId() + "'"+ "WHERE id = " + p.getId();
            
           

            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    }

