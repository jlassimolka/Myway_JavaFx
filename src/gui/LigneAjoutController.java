/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import tests.NewFXMain;
import tn.esprit.entity.LigneTransport;
import tn.esprit.entity.Moyentp;
import tn.esprit.entity.Trajet;
import tn.esprit.services.ServiceLigneTransport;
import tn.esprit.services.MoyentpService;
import tn.esprit.services.ServiceLigneTransport;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class LigneAjoutController implements Initializable {

    private TextField TextId;
    @FXML
    private TextField TextMoyen;
    @FXML
    private TextField TextTrajet;
    @FXML
    private Button AjouterID;
    @FXML
    private Button ModifierID;
    @FXML
    private Button SupprimerID;
    @FXML
    private TableView<LigneTransport> table;
    @FXML
    private TableColumn<LigneTransport, Integer> idCol;
    @FXML
    private TableColumn<LigneTransport, String> moyenCol;
    private TableColumn<LigneTransport, String> trajetCol;
    ServiceLigneTransport ss = new ServiceLigneTransport();
    MoyentpService cc = new MoyentpService();

    @FXML
    private TextField recherche;

    List<LigneTransport> lig = ss.getAll();
    List<Moyentp> mp = cc.getAll();
    @FXML
    private TableColumn<LigneTransport,String> trajetce;
    @FXML
    private TableColumn<LigneTransport,Integer > transportid;
    @FXML
    private TableColumn<LigneTransport,Integer > trajetid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<LigneTransport> listLig = FXCollections.observableArrayList(lig);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        moyenCol.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getMoyenTransport().getNom()));
        trajetce.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getTrajet().getDestination()));
       trajetid.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getTrajet().getId()));
       transportid.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getMoyenTransport().getId()));
       

        table.setItems(listLig);
    }
    @FXML
    private void AjouterAction(ActionEvent event) {

        List<LigneTransport> List;

        if (isInputValid()) {
            LigneTransport s = new LigneTransport();

            Moyentp m = new Moyentp();
            m.setId(Integer.parseInt(TextMoyen.getText()));
            s.setMoyenTransport(m);

            Trajet t = new Trajet();
            t.setId(Integer.parseInt(TextTrajet.getText()));
            s.setTrajet(t);

            ss.ajouter(s);
            reset();

            List<LigneTransport> list = ss.getAll();
            table.setItems(FXCollections.observableList(list));

            Notifications.create()
                    .title("New Ligne Added")
                    .text("A new Ligne has been added successfully!")
                    .showInformation();
        }

    }

    private void reset() {

        TextMoyen.setText("");
        TextTrajet.setText("");

    }

    @FXML
    private void ModifierAction(ActionEvent event) {
        List<LigneTransport> List;

        if (isInputValid()) {

            LigneTransport s = new LigneTransport();

            //s.setId_moyentp(TextMoyen.getText());
            //String moyentpid = s.getId_moyentp();
            int x = Integer.parseInt(TextMoyen.getText());
            s.getTrajet().setId(x);

            int y = Integer.parseInt(TextTrajet.getText());
            s.getMoyenTransport().setId(y);

            ss.modifier(s);
            Notifications.create()
                    .title("New Ligne Updated")
                    .text("A new Ligne has been updated successfully!")
                    .showInformation();
            reset();
            List = FXCollections.observableList(ss.getAll());
            table.setItems((ObservableList<LigneTransport>) List);

        }

    }

    @FXML
    private void SupprimerAction(ActionEvent event) {

        List<LigneTransport> List;

        if (null == table.getSelectionModel().getSelectedItem()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("choisir une ligne");
            alert.showAndWait();
        } else {
            LigneTransport clicked = table.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("supprimer?");
            alert.showAndWait();

            //System.out.println(clicked);
            ss.supprimer(clicked);
            Notifications.create()
                    .title("New Linge deleted")
                    .text("A new Linge has been deleted successfully!")
                    .showInformation();
            //updating user data after closing popup
            List = FXCollections.observableList(ss.getAll());
            table.setItems((ObservableList<LigneTransport>) List);

        }
    }

    @FXML
    private void recherche(KeyEvent event) {
        String searchText = recherche.getText() + event.getText();
        ObservableList<LigneTransport> filteredAbonnements = FXCollections.observableArrayList();
        for (LigneTransport ligneTransport : lig) {
            if (String.valueOf(ligneTransport.getId()).contains(searchText)) {
                filteredAbonnements.add(ligneTransport);
            }
        }
        table.setItems(filteredAbonnements);
    }

    @FXML
    public void getSelected(MouseEvent event) {
        int index = -1;
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
      
        TextMoyen.setText(trajetid.getCellData(index).toString());
        TextTrajet.setText(transportid.getCellData(index).toString());

    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (TextTrajet.getText() == null || TextTrajet.getText().isEmpty() || !TextTrajet.getText().matches("\\d+") || Integer.parseInt(TextTrajet.getText()) <= 0) {
            errorMessage += "Invalide Trajet!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Invalide champs");
            alert.setHeaderText("***Please correct champs **");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
