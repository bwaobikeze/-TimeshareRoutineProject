package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class processingRoutine {
    int time=0;
    int busyCores=0;
    int numberOfCores;
    int numberOFSSD=1;
    stateObj SSD= new stateObj();
    stateObj IO = new stateObj();
    ArrayList<stateObj>CoreList= new ArrayList<>();
    ArrayList<SubProcess> eventQueue= new ArrayList<>();
    ArrayList <SubProcess> CoreReadyQueue = new ArrayList<>();
    ArrayList<SubProcess> SSDQueue =new ArrayList<>();
    ArrayList<process> ProcessList = new ArrayList<>();
    ArrayList<ArrayList>subProPointers = new ArrayList<>();
    // modify to pick the correct process
//    SubProcess getLowestTimeValueSubProcess (int[] subProcessIters, int loadingTime){
//        SubProcess lowestTImeValueSubProcess = new SubProcess();
//        // check for index of -1
//        for(int processIdx = 0; processIdx < subProcessIters.length; processIdx++){
//            if(subProcessIters[processIdx] < 0)
//                continue;
//            SubProcess currentSubProcess = ProcessList.get(processIdx).ProcessEvents.get(subProcessIters[processIdx]);
//            if(lowestTImeValueSubProcess.subProcessName == null){
//                lowestTImeValueSubProcess = currentSubProcess;
//            } else {
//                int completionTIme = loadingTime + currentSubProcess.timeRequest;
//                if(completionTIme < loadingTime + lowestTImeValueSubProcess.timeRequest){
//                    lowestTImeValueSubProcess = currentSubProcess;
//                }
//            }
//        }
//        return lowestTImeValueSubProcess;
//    }

    void creatingEventList() {
        int intsubpointterIndex=0;
        // check if first start has been found
        boolean firstStartFound = false;
        // local time
        int loadingTime = 0;
        // initialize list of pointers
        int[] subProIters = new int[ProcessList.size()];
        // set all initial pointer valuse to zero
        Arrays.fill(subProIters, 0);
        // find total number of executions
        int totalSubProcessesLen = 0;
        for (int processIdx = 0; processIdx < ProcessList.size(); processIdx++) {
            totalSubProcessesLen += ProcessList.get(processIdx).ProcessEvents.size();
        }

        for (int numOfExecutions = 0; numOfExecutions < totalSubProcessesLen; numOfExecutions++) {
            SubProcess lowestTimeValueProcess = ProcessList.get(intsubpointterIndex).ProcessEvents.remove(subProIters[intsubpointterIndex]);
            eventQueue.add(lowestTimeValueProcess);
            if(ProcessList.get(intsubpointterIndex).ProcessEvents.isEmpty()){
                intsubpointterIndex++;
            }
        }
    }

    void RoutineLoop(){
        while(!eventQueue.isEmpty()){
            SubProcess currentProcess=eventQueue.remove(0);
        if(currentProcess.subProcessName.equals("START")){
            ArrivleTime(currentProcess);
        }
        else if(currentProcess.subProcessName.equals("CPU")){
            CoreAvailablity(currentProcess);
        }
        else if(currentProcess.subProcessName.equals("SSD")){
            SSDRequest(currentProcess);
        }
        else if(currentProcess.subProcessName.equals("OUTPUT")||currentProcess.subProcessName.equals("INPUT")){
            inputOutputRequest(currentProcess);
        }
        else{
            System.out.println("Process "+ currentProcess.ProcessNumber+" is terminaated at "+time+"ms");
        System.out.println("Current number of busy cores: "+busyCores);
            System.out.println("Ready Queue has Processes ");
            System.out.println("====================");
        }
//
}
    }

    void ArrivleTime(SubProcess arrivePro){

        System.out.println("Process "+arrivePro.ProcessNumber+" starts at t="+arrivePro.getTimeRequest()+"ms");
        time+=arrivePro.timeRequest;
        String processInReadyQueue="";
        System.out.println("Current number of busy cores: "+busyCores);
        System.out.println("Ready Queue has Processes "+ processInReadyQueue);
        System.out.println("====================");


    }
    public void CoreAvailablity(SubProcess CoreProcessReady1){

        if(numberOfCores>0){
            numberOfCores--;
            busyCores++;
            coreComplete(CoreProcessReady1);
        }
        else{
            CoreProcessReady1.ProcessState="Ready";
            CoreReadyQueue.add(CoreProcessReady1);
        }


    }

    void coreComplete(SubProcess CoreProcessReady){
            CoreProcessReady.ProcessState="Ready";
            CoreReadyQueue.add(CoreProcessReady);
            if(! CoreReadyQueue.isEmpty()){
                SubProcess tempProcess= CoreReadyQueue.remove(0);
                tempProcess.ProcessState="Running";
                time+=tempProcess.getTimeRequest();
                numberOfCores++;
                busyCores--;
            }


    }
    void inputOutputRequest(SubProcess inputOutputPro){
        time+=inputOutputPro.getTimeRequest();
    }
    void SSDRequest(SubProcess SSDPro){
        if(numberOFSSD>0){
            numberOFSSD--;
            ssdCompletionEvent(SSDPro);
        }
        else{
            SSDQueue.add(SSDPro);
        }
    }
    void ssdCompletionEvent(SubProcess newSSD){
        SSDQueue.add(newSSD);
        while(!SSDQueue.isEmpty()){
            SubProcess tempProcess=SSDQueue.remove(0);
            time+=tempProcess.timeRequest;
            numberOFSSD++;
        }
    }
//    void lockRequest(process LockPro){
//        System.out.println("lockRequest Function");
//    }

}
