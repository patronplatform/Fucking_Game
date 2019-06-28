/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication8.Control;

import java.awt.Desktop;
import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Index extends Application{
    private static void SingleRandomPlaying(){
        
    }
    private static void ShowHistory(){
        
    }
    private static void SingleCustomPlaying(){
        
    }
    private static void OnilePlaying(){
        
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        
       Stage Stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("javafxapplication8\\View\\Index.fxml"));
        Stage.setTitle("Add Category");
        Stage.setScene(new Scene(root, 541, 47));
        
        Stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
