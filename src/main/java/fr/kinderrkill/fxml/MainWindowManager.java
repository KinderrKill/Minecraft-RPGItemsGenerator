package fr.kinderrkill.fxml;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowManager implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("List : " + ItemsGenerator.getAllFilesFromResource());
        ObservableList<String> items = FXCollections.observableArrayList(

        );
        itemsList.setItems(items);
    }

    @FXML
    void actionPerformed(ActionEvent event) {
        if (event.getSource() == quitButton) {
            ItemsGenerator.getInstance().close();

            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    /*
     * FXML Variables
     */
    @FXML
    private Button quitButton;

    @FXML
    private ListView<String> itemsList;
}
