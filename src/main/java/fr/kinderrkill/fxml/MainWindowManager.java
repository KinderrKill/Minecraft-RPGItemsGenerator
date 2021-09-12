package fr.kinderrkill.fxml;

import fr.kinderrkill.ItemsGenerator;
import fr.kinderrkill.JSONFileManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class MainWindowManager implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Default config
        this.textFieldSelectedItem.setDisable(true);
        this.textFieldSelectedItem.setStyle("-fx-opacity: 1;");

        initItemsList(false);
    }

    @FXML
    void actionPerformed(ActionEvent event) {
        if (event.getSource() == quitButton) {
            ItemsGenerator.getInstance().close();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }

        // Items selections buttons
        else if (event.getSource() == itemsSelectionButton) {
            initItemsList(false);
        } else if (event.getSource() == armorSelectionButton) {
            initItemsList(true);
        }
    }

    @FXML
    void actionGeneratePerformed(ActionEvent event) {
        JSONFileManager jsonFileManager = new JSONFileManager();
        Map<String, String> data = new HashMap<>();

        if (!fieldDamageMin.getText().isEmpty() && !fieldDamageMax.getText().isEmpty())  data.put("Dégât", "+" + fieldDamageMin.getText() + "-" + fieldDamageMax.getText());

        if (!fieldArmor.getText().isEmpty()) data.put("Armure", fieldArmor.getText());

        if (!fieldAgility.getText().isEmpty()) data.put("Agilité", fieldAgility.getText());
        if (!fieldBlock.getText().isEmpty()) data.put("Parade", fieldBlock.getText());
        if (!fieldStamina.getText().isEmpty()) data.put("Endurance", fieldStamina.getText());
        if (!fieldCCDamage.getText().isEmpty()) data.put("Dégâts CC", fieldCCDamage.getText());
        if (!fieldCCChance.getText().isEmpty()) data.put("Chance CC", fieldCCChance.getText());

        if (!fieldSpeed.getText().isEmpty()) data.put("Vitesse", fieldSpeed.getText());

        if (!fieldLvNeeded.getText().isEmpty()) data.put("Level requis", fieldLvNeeded.getText());

        List<String> lores = new ArrayList<>();
        lores.add("Test Lore 1");

        jsonFileManager.test(textFieldSelectedItem.getText().toUpperCase(), fieldFileName.getText(), fieldCustomName.getText(), Integer.parseInt(fieldLevelObject.getText()), data, lores);
    }

    @FXML
    void onMouseClicked(MouseEvent event) {
        String selectedItem = itemsList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            selectedItem = selectedItem.substring(selectedItem.lastIndexOf("/") + 1, selectedItem.lastIndexOf(".png"));
        }
        this.textFieldSelectedItem.setText(selectedItem);
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

    @FXML
    private Button itemsSelectionButton;

    @FXML
    private Button armorSelectionButton;

    @FXML
    private Text textSelectedItem;

    @FXML
    private TextField textFieldSelectedItem;

    @FXML
    private Text textCustomName;

    @FXML
    private TextField fieldCustomName;

    @FXML
    private Text textDamage;

    @FXML
    private TextField fieldDamageMin;

    @FXML
    private TextField fieldDamageMax;

    @FXML
    private Text textFileName;

    @FXML
    private TextField fieldFileName;

    @FXML
    private Text textLevelObject;

    @FXML
    private TextField fieldLevelObject;

    @FXML
    private Text textAgility;

    @FXML
    private TextField fieldAgility;

    @FXML
    private Text textBlock;

    @FXML
    private TextField fieldBlock;

    @FXML
    private Text textArmor;

    @FXML
    private TextField fieldArmor;

    @FXML
    private Text textStamina;

    @FXML
    private TextField fieldStamina;

    @FXML
    private Text textCCDamage;

    @FXML
    private TextField fieldCCDamage;

    @FXML
    private Text textCCChance;

    @FXML
    private TextField fieldCCChance;

    @FXML
    private Text textSpeed;

    @FXML
    private TextField fieldSpeed;

    @FXML
    private Text textLvNeeded;

    @FXML
    private TextField fieldLvNeeded;

    @FXML
    private Button generateButton;
}
