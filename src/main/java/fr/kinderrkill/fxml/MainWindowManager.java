package fr.kinderrkill.fxml;

import fr.kinderrkill.ItemsGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class MainWindowManager implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initItemsList(false);
    }

    @FXML
    void actionPerformed(ActionEvent event) {
        if (event.getSource() == quitButton) {
            ItemsGenerator.getInstance().close();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    private void initItemsList(boolean armor) {
        List<File> list = new ArrayList<>();

        try {
            if (armor) list = ItemsGenerator.getInstance().getAllFilesFromResource("assets/armors/");
            else list = ItemsGenerator.getInstance().getAllFilesFromResource("assets/items/");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        Map<String, Image> mapOfImages = new HashMap<>();

        for (File file : list) {
            Image img = new Image(file.toURI().toString());
            mapOfImages.put(file.toURI().toString(), img);
        }

        ObservableList<String> items = FXCollections.observableArrayList(
                mapOfImages.keySet()
        );
        itemsList.setItems(items);

        itemsList.setCellFactory(param -> new ListCell<String>() {
            private final ImageView imageView = new ImageView();
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if(mapOfImages.containsKey(name))
                        imageView.setImage(mapOfImages.get(name));
                    setText(name.substring(name.lastIndexOf("/") + 1, name.lastIndexOf(".png")));
                    setGraphic(imageView);
                }
            }
        });
    }

    /*
     * FXML Variables
     */
    @FXML
    private Button quitButton;

    @FXML
    private ListView<String> itemsList;
}
