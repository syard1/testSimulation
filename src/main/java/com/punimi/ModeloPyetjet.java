package com.punimi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ModeloPyetjet{

    public static ArrayList<Pyetjet> al =new ArrayList<>(3); //Lista qe mbane pyetjet
    ArrayList<double[]> subsets;      //Lista qe mbane te gjitha "nenbashkesite ku shuma e elementeve eshte 100"
    double[] randomSubset = new double[10];    //Vargu qe mbane nje nenbashkesi te vetme ku shuma e elementeve te tyre eshte 100
    ArrayList<Integer> getIndexofQuestions;    //Lista qe mbane indeksat e pyetjeve te cilat duhet te paraqiten ne test
    private int nrIDeletedQuestion;            //variabla qe inicializohet me indeksin e elementit qe duhet te fshihet
    private Pyetjet p;      //Variabla qe mbane referencen e klases Pyetjet

    //Konstruktori i klases
    public ModeloPyetjet(){
        subsets=  new ArrayList<>();
        getIndexofQuestions = new ArrayList<>();
    }

        //Shtimi i nje pyetje ne ArrayList
    public void shtoPyetje(String pyetjaString,double piketDouble,String opsioni1String,String opsioni2String,String opsioni3String) throws IllegalArgumentException{
        if(pyetjaString.length() == 0){
            throw new IllegalArgumentException("Ju duhet te vendosni nje pyetje");
        }
        if(piketDouble <= 0.0 ){
            throw new IllegalArgumentException("piket duhet te jene pozitive");
        }
       
        else{
        al.add(new Pyetjet(pyetjaString, piketDouble, opsioni1String, opsioni2String, opsioni3String));
    }
}


    //Fshirja e nje pyetje ne indeksin e caktuar nga ArrayLista
    public void deleteQuestion(String DeleteQuestionNumberID ) throws IndexOutOfBoundsException{
         try{

        nrIDeletedQuestion=Integer.valueOf(DeleteQuestionNumberID);
        al.remove(nrIDeletedQuestion-1);
         }
         catch(IndexOutOfBoundsException ex){
         trajtoThrow("Ne bazen e te dhenave keni vetem "+al.size(), "Nuk keni fshire pyetje");
         }
   
}


        //Paraqitja e te gjitha pyetjeve qe ndooshen ne bazen e te dhenave
    public String allQuestion() throws IllegalArgumentException{
       if(al.size() ==0){
        throw new IllegalArgumentException("Nuk ka ndonje pyetje te vendosur");
    }
        String q="";
        for(int i=0;i<al.size();i++){
        p=al.get(i);
        q+=(i+1)+")"+p.getQuestion()+"\n"+"\n";
        }
        return q;
    }

        //Metoda qe bene gjenerimin e testit
    public String testQuestion() throws IllegalArgumentException{
        if(al.size() ==0){
            throw new IllegalArgumentException("Nuk ka asnje pyetje te vendosur");
        }
        this.generateSubsets();
        if(subsets.size() ==0){
            throw new IllegalArgumentException("Ju nuk keni test qe ploteson kushtin per 100 pike");
    }
        this.getRandomSubset();
        this.getIndicesOfNonZeroElements();
        String q="";
        for(int i=0;i<getIndexofQuestions.size();i++){
            int index=getIndexofQuestions.get(i);
            q+=(i+1)+")"+al.get(index).getQuestion()+"\n"+"\n";
            }
            return q;
     } 


     // Gjenerimi i  nenbashkesive  
    public void generateSubsets() {
        int n = Pyetjet.piketTotal.size();
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


       //Zgjedhja e nje nenbashkesie
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

    
     //Trajtimi i Throw
     public void trajtoThrow(String mesazhi,String komenti){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(mesazhi);
        alert.setContentText(komenti);
        alert.showAndWait();
       
     }





}