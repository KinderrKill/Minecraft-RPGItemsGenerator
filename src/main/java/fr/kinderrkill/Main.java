package fr.kinderrkill;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.InputStream;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
         ItemsGenerator itemsGenerator = new ItemsGenerator();

        launch(args);
    }

    private final String WINDOW_TITLE = "Minecraft - RPG Items Generator | Made by KinderrKill";

    private double xOffset = 0;
    private double yOffset = 0;


    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = getClass().getResource("/fxml/MainWindow.fxml");
        if (url == null) {
            //Dialogs.showFXMLFileNotFound();
            return;
        }
        Parent root = FXMLLoader.load(url);

        InputStream icon = Main.class.getResourceAsStream("/default_icon.png");
        if (icon != null)
            primaryStage.getIcons().add(new Image(icon));

        primaryStage.setTitle(WINDOW_TITLE);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    // JAVA FX
    /*private final Image IMAGE_RUBY  = new Image("http://antaki.ca/bloom/img/windows_64x64.png");
    private final Image IMAGE_APPLE  = new Image("http://antaki.ca/bloom/img/windows_64x64.png");
    private final Image IMAGE_VISTA  = new Image("http://antaki.ca/bloom/img/windows_64x64.png");
    private final Image IMAGE_TWITTER = new Image("http://antaki.ca/bloom/img/windows_64x64.png");

    private Image[] listOfImages = {IMAGE_RUBY, IMAGE_APPLE, IMAGE_VISTA, IMAGE_TWITTER};

    @Override
    public void start(Stage primaryStage) throws Exception {

        ListView<String> listView = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (
                "RUBY", "APPLE", "VISTA", "TWITTER");
        listView.setItems(items);

        listView.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if(name.equals("RUBY"))
                        imageView.setImage(listOfImages[0]);
                    else if(name.equals("APPLE"))
                        imageView.setImage(listOfImages[1]);
                    else if(name.equals("VISTA"))
                        imageView.setImage(listOfImages[2]);
                    else if(name.equals("TWITTER"))
                        imageView.setImage(listOfImages[3]);
                    setText(name);
                    setGraphic(imageView);
                }
            }
        });
        VBox box = new VBox(listView);
        box.setAlignment(Pos.CENTER);
        Scene scene = new Scene(box, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/

}
