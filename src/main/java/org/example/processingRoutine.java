package org.example;
import  org.example.process;
import org.example.core.*;

import java.util.ArrayList;
import java.util.Scanner;

public class processingRoutine {
    int time=0;
    int numberOfCores;
    ArrayList<process>ProcessList = new ArrayList<>();
    ArrayList<core>CoreList= new ArrayList<>();
    void CreateProcessObj(String read){
        System.out.println(read);
    }

    void RoutineLoop(){

    }
    void ArrivleTime(int ArrivleTime, int ProcessId){
        System.out.println("Process "+ProcessId+" starts at t="+ArrivleTime+"ms");

    }
    boolean CoreAvailablity(){
        for( int i=0; i< CoreList.size();i++){
            if(CoreList.get(i).availabilty==true){
                return true;
            }
        }
        return false;
    }

    void coreRequest(process CoreProcess){
        ArrayList<process> coreReadyQueue= new ArrayList<>();



        System.out.println("coreRequest Function");
    }
    void inputOutputRequest(int ArrivleTime,int ProcessId){
        System.out.println("inputOutputRequest Function");
    }
    void SSDRequest(int ArrivleTime,int ProcessId){
        System.out.println("SSDRequest Function");
    }
    void lockRequest(int ArrivleTime,int ProcessId){
        System.out.println("lockRequest Function");
    }
}
