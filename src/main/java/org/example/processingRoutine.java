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

    ArrayList<process> coreReadyQueue= new ArrayList<>();
    void CreateProcessObj(String read){
        System.out.println(read.charAt(0));

    }

    void RoutineLoop(){
        while(!ProcessList.isEmpty()){
            process currentProcess=ProcessList.remove(0);
            if(currentProcess.subProcessName=="START"){
                ArrivleTime(currentProcess);
            }
            else if(currentProcess.subProcessName=="CPU"){
                coreRequest(currentProcess);
            }
        }

    }
    void ArrivleTime(process arrivePro){
        int busyCores=0;
        String processInReadyQueue="";
        for(int i =0; i<CoreList.size(); i++){
            if(CoreList.get(i).availabilty==false){
                busyCores++;
            }
        }
        for(int j=0; j<coreReadyQueue.size();j++){
            processInReadyQueue+=Integer.toString(coreReadyQueue.get(j).processNum);
            processInReadyQueue+=",";

        }
        System.out.println("Process "+arrivePro.processNum+" starts at t="+arrivePro.timeRequest+"ms");
        System.out.println("Current number of busy cores: "+busyCores);
        System.out.println("Ready Queue has Processes "+processInReadyQueue);



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
