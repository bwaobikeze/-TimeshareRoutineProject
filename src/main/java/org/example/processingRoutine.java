package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class processingRoutine {
    Comparator<SubProcess> subProcessComparator = new Comparator<SubProcess>() {
        @Override
        public int compare(SubProcess o1, SubProcess o2) {
            return o1.CompletionTime- o2.CompletionTime;
        }
    };
    int time=0;
    int busyCores=0;
    int numberOfCores;
    int numberOFSSD=1;
    stateObj SSD= new stateObj();
    stateObj IO = new stateObj();
    ArrayList<stateObj>CoreList= new ArrayList<>();
    PriorityQueue<SubProcess> eventQueue= new PriorityQueue<>(subProcessComparator);
    ArrayList <SubProcess> CoreReadyQueue = new ArrayList<>();
    ArrayList<SubProcess> SSDQueue =new ArrayList<>();
    ArrayList<process> ProcessList = new ArrayList<>();
    ArrayList<ArrayList>subProPointers = new ArrayList<>();

    // modify to pick the correct process
    SubProcess getLowestTimeValueSubProcess (int[] subProcessIters, int loadingTime){
        SubProcess lowestTImeValueSubProcess = new SubProcess();
        SubProcess currentSubProcess;
        int completionTIme;
        // check for index of -1
        for(int processIdx = 0; processIdx < subProcessIters.length; processIdx++){
            if(subProcessIters.length==1){
                lowestTImeValueSubProcess=ProcessList.get(processIdx).ProcessEvents.remove(subProcessIters[processIdx]);
                 lowestTImeValueSubProcess.CompletionTime= loadingTime + lowestTImeValueSubProcess.timeRequest;
                return lowestTImeValueSubProcess;
            }
            else{
                currentSubProcess = ProcessList.get(processIdx).ProcessEvents.get(subProcessIters[processIdx]);
                  completionTIme = loadingTime + currentSubProcess.timeRequest;
                if(completionTIme < loadingTime + lowestTImeValueSubProcess.timeRequest||processIdx==0){
                    currentSubProcess.CompletionTime=completionTIme;
                    lowestTImeValueSubProcess = currentSubProcess;
                }
            }
//            if(subProcessIters[processIdx] < 0)
//                continue;
//            SubProcess currentSubProcess = ProcessList.get(processIdx).ProcessEvents.get(subProcessIters[processIdx]);
//            if(lowestTImeValueSubProcess.subProcessName == null){
//                lowestTImeValueSubProcess = currentSubProcess;
//            } else {
//                int completionTIme = loadingTime + currentSubProcess.timeRequest;
//                if(completionTIme < loadingTime + lowestTImeValueSubProcess.timeRequest){
//                    currentSubProcess.CompletionTime=completionTIme;
//                    lowestTImeValueSubProcess = currentSubProcess;
//                }
//            }
        }

        return lowestTImeValueSubProcess;
    }

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
//            SubProcess lowestTimeValueProcess = ProcessList.get(intsubpointterIndex).ProcessEvents.remove(subProIters[intsubpointterIndex]);
            SubProcess lowestTimeValueProcess=getLowestTimeValueSubProcess(subProIters,loadingTime);
            loadingTime=lowestTimeValueProcess.CompletionTime;
            if(subProIters[lowestTimeValueProcess.ProcessNumber]<ProcessList.get(lowestTimeValueProcess.ProcessNumber).ProcessEvents.size()){
                subProIters[lowestTimeValueProcess.ProcessNumber]++;
            }
            //subProIters[lowestTimeValueProcess.ProcessNumber]++;
//          if(lowestTimeValueProcess.subProcessName.equals("START")){
//              if(firstStartFound==false){
//                  loadingTime=lowestTimeValueProcess.CompletionTime;
//                  ProcessList.get(lowestTimeValueProcess.ProcessNumber).ProcessEvents.remove(subProIters[0]);
//                  firstStartFound=true;
//              }
//          }
            eventQueue.add(lowestTimeValueProcess);
//            if(ProcessList.get(intsubpointterIndex).ProcessEvents.isEmpty()){
//                intsubpointterIndex++;
//            }
        }
    }

    void RoutineLoop(){
        while(!eventQueue.isEmpty()){
            SubProcess currentProcess=eventQueue.poll();
            time=currentProcess.CompletionTime;
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
            ProcessList.get(currentProcess.ProcessNumber).ProcessState="Terminated";
            System.out.println("Process "+ currentProcess.ProcessNumber+" is Terminated at "+time+"ms");
            System.out.println("Current number of busy cores: "+busyCores);
            System.out.println("Process "+currentProcess.ProcessNumber+" Is "+ ProcessList.get(currentProcess.ProcessNumber).ProcessState);
            System.out.println("====================");
        }
//
}
    }


    void ArrivleTime(SubProcess arrivePro){
        ProcessList.get(arrivePro.ProcessNumber).ProcessState="READY";
        System.out.println("Process "+arrivePro.ProcessNumber+" starts at t="+arrivePro.getTimeRequest()+"ms");
        System.out.println("Current number of busy cores: "+busyCores);
        System.out.println("Process "+arrivePro.ProcessNumber+" Is "+ ProcessList.get(arrivePro.ProcessNumber).ProcessState);
        System.out.println("====================");


    }
    public void CoreAvailablity(SubProcess CoreProcessReady1){

        if(numberOfCores>0){
            numberOfCores--;
            busyCores++;
            coreComplete(CoreProcessReady1);
        }
        else{
            //ProcessList.get(CoreProcessReady1.ProcessNumber).ProcessState="READY";
            CoreReadyQueue.add(CoreProcessReady1);
        }


    }

    void coreComplete(SubProcess CoreProcessReady){
            //CoreProcessReady.ProcessState="Ready";
            CoreReadyQueue.add(CoreProcessReady);
            if(! CoreReadyQueue.isEmpty()){
                SubProcess tempProcess= CoreReadyQueue.remove(0);
                //tempProcess.ProcessState="Running";
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
            numberOFSSD++;
        }
    }
//    void lockRequest(process LockPro){
//        System.out.println("lockRequest Function");
//    }

}
