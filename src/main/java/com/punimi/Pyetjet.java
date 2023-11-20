package com.punimi;

import java.util.ArrayList;

public class Pyetjet {
    private String pyetja;
    private double piket;
    private String opcioni1;
    private String opcioni2;
    private String opcioni3;
    public static ArrayList<Double> piketTotal=new ArrayList<>(2);   //ArrayLista qe mbane piket per pyetje

    //Konstruktimi i pyetjeve
    public Pyetjet(String pyetja,double piket,String opsioni1,String opsioni2,String opsioni3){
        this.pyetja=pyetja;
        this.piket=piket;
        this.opcioni1=opsioni1;
        this.opcioni2=opsioni2;
        this.opcioni3=opsioni3;
        piketTotal.add(piket);
    }


    public double getPiket(){
        return piket;
    }


    //Afishimi i pyetjeve
    public String getQuestion(){
        if(opcioni1!=null && opcioni2 !=null && opcioni3!=null){
        String perfundimtare=pyetja+"?"+"\t "+piket+"(piket)"+"\n a)"+opcioni1+"\t b)"+opcioni2+"\t c)"+opcioni3;
        return perfundimtare;
        }
        return pyetja+"?"+"\t "+piket+"(piket)";
    }

}
