package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class processingRoutine {
    int time=0;
    int numberOfCores;
    stateObj SSD= new stateObj();
    stateObj IO = new stateObj();
    ArrayList<stateObj>CoreList= new ArrayList<>();
    ArrayList<SubProcess> eventQueue= new ArrayList<>();
    ArrayList<process> ProcessList = new ArrayList<>();
    ArrayList<ArrayList>subProPointers = new ArrayList<>();

/*
*  Loads event list of processes
*  approach:
*   create and set loadingClock to 0
*   create and store subProcessPointers list [0,0]
*   while the "subProPointers" list is not empty
*       get the lowest "*time value" from "subProPointers"
*       get and store lowest time value type
*       if lowest time value type is Start
*           * do stuff
*       else if lowest time value type is end
*           * do stuff
*       else
*           * do stuff to regular process type i.e. SSD, CPU, Lock, IO
*
* */

    void creatingEventList(){
        // initialize list of pointers
        int[] subProIters = new int[ProcessList.size()];
        // set all initial pointer valuse to zero
        Arrays.fill(subProIters, 0);
        // print all subPorcessess using pointer array
        for(int processIdx = 0; processIdx < ProcessList.size(); processIdx++){
            ArrayList<SubProcess> currentSubProcessList = ProcessList.get(processIdx).ProcessEvents;
//            System.out.println(currentSubProcessList.get(processIdx).getSubProcessName());
            while(subProIters[processIdx] < currentSubProcessList.size()){
                SubProcess currentSubProcess = currentSubProcessList.get(subProIters[processIdx]);
                System.out.println(currentSubProcess.getSubProcessName());
                System.out.println(currentSubProcess.getTimeRequest());
                subProIters[processIdx] += 1;
            }
        }

//        int loadingCloack=0;
//        SubProcess currentGretestEvent;
//        for(int i=0;i<ProcessList.size();i++){
//            ArrayList SubprocessPointer=ProcessList.get(i).ProcessEvents;
//            subProPointers.add(SubprocessPointer);
//        }
//        while(!subProPointers.isEmpty()){
//
//        }
    }

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
