package com.punimi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.ColorPicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
public class SceneController{
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField pyetjaTextFieldID;
    @FXML
    private TextField piketTextFieldID;
    @FXML
    private TextField opsioni1TextFieldID;
    @FXML
    private TextField opsioni2TextFieldID;
    @FXML
    private TextField opsioni3TextFieldID;
    @FXML
    private Label GjeneroTeGjithaPytjetLabel;
    @FXML
    private TextField DeleteQuestionNumberID;
    @FXML
    public Button submitDeleteButtonId;
    @FXML
    public Button VendosiPytjetButtonID;
    @FXML
    private Label GjeneroTestinLabelID;
    @FXML
    public String pyetjaString;
    public double piketDouble;
    public String opsioni1String;
    public String opsioni2String;
    public String opsioni3String;
    private int nrIDeletedQuestion;
    ModeloPyetjet pyetja;

    //Konstruktori i klases
    public SceneController(){
      pyetja = new ModeloPyetjet();
    }



    //Kalohet tek dritarja kryesore
    public void switchToScene(ActionEvent event) throws IOException
    {
         Parent root=FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //Kalohet tek dritarja per vendosjen e pyetjeve
    public void switchToVendosiPytjetScene(ActionEvent event) throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource("VendosiPyetjetScene.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //Kalohet tek dritarja e cila gjeneron te gjitha pyetjet
    public void switchToGjeneroTeGjithaPyetjetScene(ActionEvent event) throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource("GjeneroTeGjithaPyetjetScene.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //Kalohet tek dritarja qe gjenron testin
    public void switchToGjeneroTestinScene(ActionEvent event) throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource("GjeneroTestinScene.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    //Zgjedhim nese deshirojme te vazhdojme pyetjen me opcione
    public void opsionetCheckBox(ActionEvent event){
        if(opsioni1TextFieldID.isVisible()){
            opsioni1TextFieldID.setVisible(false);
            opsioni2TextFieldID.setVisible(false);
            opsioni3TextFieldID.setVisible(false);
        }
        else{
        opsioni1TextFieldID.setVisible(true);
        opsioni2TextFieldID.setVisible(true);
        opsioni3TextFieldID.setVisible(true);
        }
    }

    //Aksioni i butonit submit dhe lidhet me vendosjen e  pyetjes ne dataBaze
    public void submit(ActionEvent event) throws  IllegalArgumentException{
        pyetjaString=pyetjaTextFieldID.getText();
    try{
        if(piketTextFieldID.getText().length() == 0){
            throw new IllegalArgumentException("Ju duhet te vendosni piket per pyetje");
        }
            piketDouble=Double.valueOf(piketTextFieldID.getText());
            if(opsioni1TextFieldID.isVisible()){
                opsioni1String=opsioni1TextFieldID.getText();
                opsioni2String=opsioni2TextFieldID.getText();
                opsioni3String=opsioni3TextFieldID.getText();
             }
             pyetja.shtoPyetje(pyetjaString,piketDouble,opsioni1String,opsioni2String,opsioni3String);
    }
    catch(NumberFormatException  ex){
                pyetja.trajtoThrow(ex.getMessage(),"Pyetja nuk mund te vendoset");
    }
    catch(IllegalArgumentException ex){
             pyetja.trajtoThrow(ex.getMessage(),"Pyetja nuk mund te vendoset");
        
    }
        pyetjaTextFieldID.clear();
        opsioni1TextFieldID.clear();
        opsioni2TextFieldID.clear();
        opsioni3TextFieldID.clear();
        piketTextFieldID.clear();
    }


        //Aksioni i shtypjes se butonit Delete
    public void submitDelete(ActionEvent event) throws IllegalArgumentException{
        try{
            String text = DeleteQuestionNumberID.getText();
            pyetja.deleteQuestion(text);

        }
        catch(IllegalArgumentException ex){
           pyetja.trajtoThrow(ex.getMessage(), "Nuk keni fshire pyetje");
       
        }}
    

    //Aksioni i shtypjes se butonit per te gjeneruar te gjitha pyetjet
    public void setGjeneroTeGjithaPytjetLabel(){
     
        try{
        String q = pyetja.allQuestion();
       GjeneroTeGjithaPytjetLabel.setText(q);
        }
        catch(IllegalArgumentException ex){
           pyetja.trajtoThrow(ex.getMessage(), "Nuk mund te gjenerohen pyetjet!");
        }
       
    }


    //Metoda qe na gjeneron Testin me 100pike
    public void setGjeneroTestinLabel(){
        String q="";
        GjeneroTestinLabelID.setText(q);
   
            try{
                q = pyetja.testQuestion();
                System.out.println(q);
                
                GjeneroTestinLabelID.setText(q);
            }
        catch(IllegalArgumentException   ex){
            pyetja.trajtoThrow(ex.getMessage(),"Testi nuk mund te gjenerohet");
        }     
    }



   //Thirret nga butoni delete per te fshehur,dukur...
     public void butoniDelete(ActionEvent event){
        if(DeleteQuestionNumberID.isVisible()){
        DeleteQuestionNumberID.setVisible(false);
        submitDeleteButtonId.setVisible(false);
    }
    else{
        DeleteQuestionNumberID.setVisible(true);
        submitDeleteButtonId.setVisible(true);
    }
     }

}
