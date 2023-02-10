package org.example;

import java.util.ArrayList;

public class processingRoutine {
    int time=0;
    int numberOfCores;
    int numberOFSSD=1;
    ArrayList<process>SSDQueue = new ArrayList<>();
    ArrayList<core>CoreList= new ArrayList<>();

    ArrayList<process> coreReadyQueue= new ArrayList<>();


//    void RoutineLoop(process currentProcess){
//        if(currentProcess.subProcessName.equals("START")){
//            ArrivleTime(currentProcess);
//        }
//        else if(currentProcess.subProcessName.equals("CPU")){
//            CoreAvailablity(currentProcess);
//        }
//        else if(currentProcess.subProcessName.equals("SSD")){
//            SSDRequest(currentProcess);
//        }
//        else if(currentProcess.subProcessName.equals("OUTPUT")||currentProcess.subProcessName.equals("INPUT")){
//            inputOutputRequest(currentProcess);
//        }
//        else{
//            System.out.println("Process "+ currentProcess.processNum+" is terminaated at "+time+"ms");
//            System.out.println("====================");
//        }
//
//
//    }

//    void ArrivleTime(process arrivePro){
//
//        System.out.println("Process "+arrivePro.processNum+" starts at t="+arrivePro.timeRequest+"ms");
//        time=arrivePro.timeRequest;
//        int busyCores=0;
//        String processInReadyQueue="";
//        for(int i =0; i<CoreList.size(); i++){
//            if(CoreList.get(i).availabilty==false){
//                busyCores++;
//            }
//        }
//        System.out.println("Current number of busy cores: "+busyCores);
//        for(int j=0; j<coreReadyQueue.size();j++){
//            processInReadyQueue+=Integer.toString(coreReadyQueue.get(j).processNum);
//            processInReadyQueue+=",";
//
//        }
//        System.out.println("Ready Queue has Processes "+ processInReadyQueue);
//        System.out.println("====================");
//
//
//    }
//    public void CoreAvailablity(process CoreProcessReady1){
//
//        if(numberOfCores>0){
//            numberOfCores--;
//            coreComplete(CoreProcessReady1);
//        }
//        else{
//            CoreProcessReady1.ProcessState="Ready";
//            coreReadyQueue.add(CoreProcessReady1);
//        }
//
//    }
//
//    void coreComplete(process CoreProcessReady){
//            CoreProcessReady.ProcessState="Ready";
//            coreReadyQueue.add(CoreProcessReady);
//            if(!coreReadyQueue.isEmpty()){
//                process tempProcess=coreReadyQueue.remove(0);
//                tempProcess.ProcessState="Running";
//                //System.out.println("POP Out List "+tempProcess.timeRequest);
//                time+=tempProcess.timeRequest;
//                numberOfCores++;
//            }
//
//
//    }
//    void inputOutputRequest(process inputOutputPro){
//        System.out.println("inputOutputRequest Function");
//    }
//    void SSDRequest(process SSDPro){
//        System.out.println("SSDRequest Function");
//        if(numberOFSSD>0){
//            numberOFSSD--;
//            ssdCompletionEvent(SSDPro);
//        }
//        else{
//            SSDQueue.add(SSDPro);
//        }
//    }
//    void ssdCompletionEvent(process newSSD){
//        SSDQueue.add(newSSD);
//        while(!SSDQueue.isEmpty()){
//            process tempProcess=SSDQueue.remove(0);
//            time+=tempProcess.timeRequest;
//            numberOFSSD++;
//        }
//    }
//    void lockRequest(process LockPro){
//        System.out.println("lockRequest Function");
//    }

}
