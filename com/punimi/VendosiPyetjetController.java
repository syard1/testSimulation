package com.punimi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class VendosiPyetjetController {
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField pyetjaTextFieldID;
    @FXML
    private Button submitButtonId;
    public static String pyetjaString;
    public void switchToScene2(ActionEvent event) throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource("VendosiPyetjetScene.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void submit(ActionEvent event){
        pyetjaString=pyetjaTextFieldID.getText();
        System.out.println(5);
        System.out.println(pyetjaString);
    }
}
