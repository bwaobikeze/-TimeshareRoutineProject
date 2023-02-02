package org.example;

import java.util.ArrayList;

public class processingRoutine {
    int numberOfCores;
    ArrayList<process>ProcessList = new ArrayList<>();

    void RoutineLoop(){

    }
    void ArrivleTime(int ArrivleTime, int ProcessId){
        System.out.println("ArrivleTime Function");
    }
    void coreRequest(int ArrivleTime, int ProcessId){

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
