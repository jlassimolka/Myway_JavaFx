/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import tn.esprit.entity.Moyentp;
import tn.esprit.tools.MaConnection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Molka
 */
public class MoyentpService implements InterfaceService<Moyentp>{
    Connection cnx;

    public MoyentpService() {
        cnx = MaConnection.getInstance().getCnx();
    }


    @Override
    public void ajouter(Moyentp t) {
        try {
            String sql;
            sql = "insert into moyentp (matricule,type,nbreplace,prix_ticket,horaire,nom) values (?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getMatricule());
            ste.setString(2, t.getType());
            ste.setInt(3, t.getNbreplace());
            ste.setFloat(4, t.getPrix_ticket());
            ste.setString(5, t.getHoraire());
            ste.setString(6, t.getNom());
      
            ste.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

     @Override
    public List<Moyentp> getAll() {
        List<Moyentp> m=new ArrayList<>();
        try {
            String sql;
            sql="select * from moyentp";
            Statement ste = cnx.prepareStatement(sql);
            
            ResultSet rs= ste.executeQuery(sql);
            while(rs.next()){
                Moyentp p =new Moyentp(rs.getString("matricule"),rs.getString("type"),rs.getInt("nbreplace"),rs.getFloat("prix_ticket"),rs.getString("horaire"),rs.getString("nom"));
                m.add(p);
            }
        } catch (SQLException ex) {
        
            
        }
    return m;
    }

    @Override
    public List<Moyentp> findByMatricule(String matricule) {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Moyentp t) {
        try {
            String sql="delete from moyentp where matricule=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getMatricule());
            ste.executeUpdate();
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
    }

    public void modifier(Moyentp p) {
        String sql = "update moyentp set type=?, nbreplace=?, prix_ticket=?, horaire=?, nom=?  where matricule=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1,p.getType());
            ste.setInt(2,p.getNbreplace());
            ste.setFloat(3,p.getPrix_ticket());
            ste.setString(4,p.getHoraire());
            ste.setString(5,p.getNom());
            ste.setString(6,p.getMatricule());
            
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    @Override
    public List<Moyentp> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void modifier(String matricule, String horaire) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
