package com.mycompany.database_grouppro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;

public class MainCoverPage extends Application {

    @Override
  
    public void start(Stage stage) throws IOException {
  
        FXMLLoader fxmlLoader = new FXMLLoader(MainCoverPage.class.getResource("CoverPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Smile Clinic - Ramallah");
        stage.getIcons().add(new Image("C:\\Users\\khaled\\Desktop\\DataBase_GroupPro\\src\\main\\resources\\MaryamProjectPhotos\\4973036.png"));
        stage.setScene(scene);
        stage.show();
    
    }


    public static void main(String[] args) {
        launch();
    }

}