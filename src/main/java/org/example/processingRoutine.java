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
*   create and set totalSubProcessesLen to length of all subProcesses
*   Loop from 0 to totalSubProcessesLen
*       get and store lowestTimeValue of all subProcesses
*       get and store lowestTimeValueType of all subProcesses
*       if lowest time value type is Start
*           * do stuff
*       else if lowest time value type is end
*           * do stuff
*       else
*           * do stuff to regular process type i.e. SSD, CPU, Lock, IO
*
* */

    SubProcess getLowestTimeValueSubProcess (int[] subProcessIters, int loadingTime){
        SubProcess lowestTImeValueSubProcess = new SubProcess();
        for(int processIdx = 0; processIdx < subProcessIters.length; processIdx++){
            SubProcess currentSubProcess = ProcessList.get(processIdx).ProcessEvents.get(subProcessIters[processIdx]);
            if(lowestTImeValueSubProcess.subProcessName == null){
                lowestTImeValueSubProcess = currentSubProcess;
            } else {
                int completionTIme = loadingTime + currentSubProcess.timeRequest;
                if(completionTIme < loadingTime + lowestTImeValueSubProcess.timeRequest){
                    lowestTImeValueSubProcess = currentSubProcess;
                }
            }
        }
        return lowestTImeValueSubProcess;
    }

    void creatingEventList(){
        // local time
        int loadingTime = 0;
        // initialize list of pointers
        int[] subProIters = new int[ProcessList.size()];
        // set all initial pointer valuse to zero
        Arrays.fill(subProIters, 0);
        // print all subPorcessess using pointer array

        // find total number of executions
        int totalSubProcessesLen = 0;
        for(int processIdx = 0; processIdx < ProcessList.size(); processIdx++){
            totalSubProcessesLen += ProcessList.get(processIdx).ProcessEvents.size();
        }

        for(int numOfExecutions = 0; numOfExecutions < totalSubProcessesLen; numOfExecutions++){
            SubProcess lowestTimeValueProcess = getLowestTimeValueSubProcess(subProIters,loadingTime);
            System.out.println(lowestTimeValueProcess.getSubProcessName());
            System.out.println(lowestTimeValueProcess.getTimeRequest());
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
