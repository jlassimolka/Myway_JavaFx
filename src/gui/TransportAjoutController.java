/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static java.awt.SystemColor.control;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tests.NewFXMain;
import tn.esprit.entity.Moyentp;
import tn.esprit.services.MoyentpService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class TransportAjoutController implements Initializable {

    @FXML
    private TextField TextMatricule;
    private TextField TextType;
    @FXML
    private TextField TextNombre;
    @FXML
    private TextField TextPrix;
    @FXML
    private TextField TextHoraire;
    @FXML
    private Button AjouterID;
    @FXML
    private Button ModifierID;
    @FXML
    private Button SupprimerID;
    @FXML
    private TableColumn<Moyentp,String> matriculeCol;
    @FXML
    private TableColumn<Moyentp,String> typeCol;
    @FXML
    private TableColumn<Moyentp,Integer> nombreCol;
    @FXML
    private TableColumn<Moyentp,Float> prixCol;
    @FXML
    private TableColumn<Moyentp,String> horaireCol;
     MoyentpService ss = new MoyentpService();
    @FXML
    private TableView<Moyentp> table;
    @FXML
    private TextField recherche;
      List<Moyentp> stat = ss.getAll();
    @FXML
    private TableColumn<Moyentp, String> nomCol;
    @FXML
    private TextField TextNom;
   
    @FXML
    private ChoiceBox<String> ChoiceBox;
    private String[] type={"bus","metro","train"};
    @FXML
    private Button LigneID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // List<Moyentp> stat;
        //stat = ss.getAll();
        ChoiceBox.getItems().addAll(type);
        ObservableList<Moyentp> listStat = FXCollections.observableArrayList(stat);
        matriculeCol.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nbreplace"));
        prixCol.setCellValueFactory(new PropertyValueFactory<>("prix_ticket"));
        horaireCol.setCellValueFactory(new PropertyValueFactory<>("horaire"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        table.setItems(listStat);
    }    

    @FXML
    private void AjouterAction(ActionEvent event) {
        List<Moyentp> List;
       if (isInputValid()) {
        Moyentp s= new Moyentp();
       
        s.setMatricule(TextMatricule.getText());
       s.setType(ChoiceBox.getValue());
         int x=Integer.parseInt(TextNombre.getText());
        s.setNbreplace(x);
        float y = Float.parseFloat(TextPrix.getText());
        s.setPrix_ticket(y);

        
          s.setHoraire(TextHoraire.getText());
          s.setNom(TextNom.getText());
          
        ss.ajouter(s);
        
        reset();
         List = FXCollections.observableList(ss.getAll());
                table.setItems((ObservableList<Moyentp>) List);
        
    }
    }
    
     private void reset() {
      
      TextMatricule.setText("");
     
      TextNombre.setText("");
      TextPrix.setText("");
      TextHoraire.setText("");
      TextNom.setText("");
      
      
    }

    @FXML
    private void ModifierAction(ActionEvent event) {
        List<Moyentp> List;
        
        if(isInputValid()){
            
              Moyentp s = new Moyentp();
             s.setMatricule(TextMatricule.getText());
             String matricule=s.getMatricule();
             
             
              
            s.setType(ChoiceBox.getValue());
              
              int x=Integer.parseInt(TextNombre.getText());
              s.setNbreplace(x);
              
              
              float y = Float.parseFloat(TextPrix.getText());
       s.setPrix_ticket(y);

              
             
             s.setHoraire(TextHoraire.getText());
             String horaire=s.getHoraire();
             
             
             s.setNom(TextNom.getText());
             String nom=s.getNom();
             
            
             ss.modifier(s);
             
             
         reset();
         List = FXCollections.observableList(ss.getAll());
                table.setItems((ObservableList<Moyentp>) List);
             
             
         }
    }
  public void switchToScene1(ActionEvent event) throws IOException {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/ligneAjout.fxml"));
        Scene scene = new Scene(root, 700, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    @FXML
    private void SupprimerAction(ActionEvent event) {
        List<Moyentp> List;
        
        
        
           if (null== table.getSelectionModel().getSelectedItem() ){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("Please select a transport from the table");
           alert.showAndWait();
           }else{
        Moyentp clicked = table.getSelectionModel().getSelectedItem();
        Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("supprimer?");
           alert.showAndWait();
        //System.out.println(clicked);
     ss.supprimer(clicked);
                //updating user data after closing popup
                List = FXCollections.observableList(ss.getAll());
                table.setItems((ObservableList<Moyentp>) List);
    
           }
    }
    @FXML
   private void recherche(KeyEvent event) {
     String searchText = recherche.getText() + event.getText();
    ObservableList<Moyentp> filteredAbonnements = FXCollections.observableArrayList();
    for (Moyentp moyentp : stat) {
        if (String.valueOf(moyentp.getMatricule()).contains(searchText) || String.valueOf(moyentp.getMatricule()).contains(searchText)) {
            filteredAbonnements.add(moyentp);
        }
    }
    table.setItems(filteredAbonnements);
    }
    
     @FXML
    public void getSelected(MouseEvent event){
        int index = -1;
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
       Moyentp m = table.getItems().get(table.getSelectionModel().getSelectedIndex());
    TextMatricule.setText(m.getMatricule());
    ChoiceBox.setValue(m.getType());
    TextNombre.setText(Integer.toString(m.getNbreplace()));
    TextPrix.setText(Float.toString(m.getPrix_ticket()));
    TextHoraire.setText(m.getHoraire());
    TextNom.setText(m.getNom());
        
    }
    
    
     private boolean isInputValid() {
        String errorMessage = "";

        if (TextMatricule.getText() == null || TextMatricule.getText().length() == 0  || TextMatricule.getText().matches("[0-9]+") ) {
            errorMessage += "Invalide Matricule!\n"; 
        }
     
        
      if (TextNombre.getText() == null || TextNombre.getText().isEmpty() || !TextNombre.getText().matches("\\d+") || Integer.parseInt(TextNombre.getText()) <= 0) {
    errorMessage += "Invalide nombre de place!\n";
}
if (TextPrix.getText() == null || TextPrix.getText().isEmpty() || !TextPrix.getText().matches("\\d+(\\.\\d+)?") || Float.parseFloat(TextPrix.getText()) <= 0) {
    errorMessage += "Invalid prix!";
}



        if (TextHoraire.getText() == null || TextHoraire.getText().length() == 0 || TextHoraire.getText().matches("[0-9]+")) {
            errorMessage += "Invalide horaire!\n"; 
        }
         if (TextNom.getText() == null || TextNom.getText().length() == 0 || TextNom.getText().matches("[0-9]+")) {
            errorMessage += "Invalide nom!\n"; 
        }
    
     if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            
            alert.setTitle("Invalide champs");
            alert.setHeaderText("***Please correct champs **");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }

    
    
}
