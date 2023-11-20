package com.punimi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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
    private Pyetjet p;
    public static ArrayList<Pyetjet> al=new ArrayList<>(3);
    ArrayList<double[]> subsets =  new ArrayList<>();
    double[] randomSubset = new double[10];
    ArrayList<Integer> getIndexofQuestions  = new ArrayList<>();
    private int nrIDeletedQuestion;

    public void switchToScene(ActionEvent event) throws IOException
    {
         Parent root=FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToVendosiPytjetScene(ActionEvent event) throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource("VendosiPyetjetScene.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToGjeneroTeGjithaPyetjetScene(ActionEvent event) throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource("GjeneroTeGjithaPyetjetScene.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToGjeneroTestinScene(ActionEvent event) throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource("GjeneroTestinScene.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
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
    public void submit(ActionEvent event){
        pyetjaString=pyetjaTextFieldID.getText();
        if(piketTextFieldID.getText()!=null){
        piketDouble=Double.valueOf(piketTextFieldID.getText());
        }
        if(opsioni1TextFieldID.isVisible()){
           opsioni1String=opsioni1TextFieldID.getText();
           opsioni2String=opsioni2TextFieldID.getText();
           opsioni3String=opsioni3TextFieldID.getText();
        }
        //Pyetjet p=new Pyetjet(pyetjaString, piketDouble, opsioni1String, opsioni2String, opsioni3String);
        al.add(new Pyetjet(pyetjaString, piketDouble, opsioni1String, opsioni2String, opsioni3String));
        System.out.println(al.size());
        pyetjaTextFieldID.clear();
        opsioni1TextFieldID.clear();
        opsioni2TextFieldID.clear();
        opsioni3TextFieldID.clear();
        piketTextFieldID.clear();
    }
    public void submitDelete(ActionEvent event){
        deleteQuestion();
    }
    public void setGjeneroTeGjithaPytjetLabel(){
        String q="";
        for(int i=0;i<al.size();i++){
        p=al.get(i);
        q+=(i+1)+")"+p.getQuestion()+"\n"+"\n";
        }
        GjeneroTeGjithaPytjetLabel.setText(q);
    }
    public void setGjeneroTestinLabel(){
        this.generateSubsets();
        this.getRandomSubset();
        this.getIndicesOfNonZeroElements();
        String q="";
        GjeneroTestinLabelID.setText(q);
        for(int i=0;i<getIndexofQuestions.size();i++){
            int index=getIndexofQuestions.get(i);
            q+=(i+1)+")"+al.get(index).getQuestion()+"\n"+"\n";
            }
            GjeneroTestinLabelID.setText(q);
            getIndexofQuestions.clear();
    }
     public void generateSubsets() {
     int n = Pyetjet.piketTotal.size();
     // Iterate over all possible combinations of elements
        for (int i = 0; i < (1 << n); i++) {
            double[] subset = new double[n];
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset[j] = Pyetjet.piketTotal.get(j);
                    sum += Pyetjet.piketTotal.get(j);
                }
            }
            if (sum == 100) {
                subsets.add(subset);
            }
        }
    } 
    public void getRandomSubset() {
        Random random = new Random();
        int index = random.nextInt(subsets.size());
        randomSubset = subsets.get(index);
     
     } 
     public void  getIndicesOfNonZeroElements() {
        for (int i = 0; i < randomSubset.length; i++) {
            if (randomSubset[i] != 0) {
              getIndexofQuestions.add(i);
            }
        }
     } 
     public void deleteQuestion(){
        System.out.println(nrIDeletedQuestion);
        if(DeleteQuestionNumberID.getText()!=null){
        nrIDeletedQuestion=Integer.valueOf(DeleteQuestionNumberID.getText());
        al.remove(nrIDeletedQuestion-1);}
     } 
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
