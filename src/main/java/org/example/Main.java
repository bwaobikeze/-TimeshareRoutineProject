package org.example;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        processingRoutine beginProcess= new processingRoutine();
        //System.out.println("Hello world2!");
        Scanner input=new Scanner(System.in);
        while(input.hasNext()){
            String text=input.nextLine();
            beginProcess.CreateProcessObj(text);

        }
        //System.out.println(beginProcess.ProcessList.get(0).subProcessName);
        for(int i=0; i<beginProcess.ProcessList.size();i++){
            System.out.println("Process: "+beginProcess.ProcessList.get(i).processNum);
            System.out.println("Subprocess: "+beginProcess.ProcessList.get(i).subProcessName);
            System.out.println("Arrivle Time: "+beginProcess.ProcessList.get(i).timeRequest);
            System.out.println("====================================");

        }


    }
}