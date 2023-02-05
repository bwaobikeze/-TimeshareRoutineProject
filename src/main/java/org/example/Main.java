package org.example;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        processingRoutine beginProcess= new processingRoutine();
        //System.out.println("Hello world2!");
        process core1= new process(1,"CPU");
        core1.timeRequest=200;
        process core2 =new process(1,"CPU");
        core2.timeRequest=100;
        process core3 =new process(1,"CPU");
        core2.timeRequest=100;
//        process endPro =new process(1,"END");
        beginProcess.CoreAvailablity(core1);
        beginProcess.CoreAvailablity(core2);
        beginProcess.CoreAvailablity(core3);
        System.out.println(beginProcess.time);
//        Scanner input=new Scanner(System.in);
//        while(input.hasNext()){
//            String text=input.next();
//            beginProcess.CreateProcessObj(text);
//        }

    }
}