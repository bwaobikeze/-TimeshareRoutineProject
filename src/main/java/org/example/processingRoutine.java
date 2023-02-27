package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class processingRoutine {
    /*************************************************************************************************************************
     * These are the properties to this ProcessingRoutine class
     **************************************************************************************************************************/
    int time=0;
    int busyCores=0;
    int numberOfCores;
    int numberOFSSD=1;
    ArrayList<SubProcess> eventQueue= new ArrayList<>();
    ArrayList <SubProcess> CoreReadyQueue = new ArrayList<>();
    ArrayList<SubProcess> SSDQueue =new ArrayList<>();
    ArrayList<process> ProcessList = new ArrayList<>();
    ArrayList<SubProcess> LockRequestList = new ArrayList<>();
    ArrayList<stateObj> SixtyFourLocks = new ArrayList<>();

    /*************************************************************************************************************************
     * This Function getLowestTimeValueSubProcess() return the process request that will be entered into the event queue first
     * based on their completion time
     **************************************************************************************************************************/
    // modify to pick the correct process
    SubProcess getLowestTimeValueSubProcess (int[] subProcessIters, int loadingTime){
        SubProcess lowestTImeValueSubProcess = new SubProcess();
        int completionTIme=Integer.MAX_VALUE;
        for(int processIdx = 0; processIdx < subProcessIters.length; processIdx++){
            /*******************************
             * Logic for 1 Process
             ********************************/
            if(subProcessIters.length==1){
                lowestTImeValueSubProcess=ProcessList.get(processIdx).ProcessEvents.remove(subProcessIters[processIdx]);
                 lowestTImeValueSubProcess.CompletionTime= loadingTime + lowestTImeValueSubProcess.timeRequest;
                return lowestTImeValueSubProcess;
            }
            /*******************************
             * Logic for multiple Process
             ********************************/
            else{
                // check for index of -1
                if(subProcessIters[processIdx]==-1){
                    continue;
                }
                //accessing the first element in the specfic proceess indicated by ProcessIDX and storing it ""currentSubProcess" varaiable
                SubProcess currentSubProcess = ProcessList.get(processIdx).ProcessEvents.get(subProcessIters[processIdx]);
                  // we are checking if the completion time is less then the current subProcess+loading time
                if(loadingTime + currentSubProcess.timeRequest <completionTIme ){
                    completionTIme=loadingTime + currentSubProcess.timeRequest;
                    currentSubProcess.CompletionTime=completionTIme;
                    lowestTImeValueSubProcess = currentSubProcess;
                }
            }
        }

        return lowestTImeValueSubProcess;
    }


    /***************************************************************************************************************************************************
    * This Function creatingEventList() combines the event list of each process read in and
    * creates a combined priority queue(which priority is based on the calculated arrival time of each event request)  to run the Process routine System
     ****************************************************************************************************************************************************/
    void creatingEventList() {
        int intsubpointterIndex=0;
        // check if first start has been found
        boolean firstStartFound = false;
        // local time
        int loadingTime = 0;
        int FirstTime=0;
        // initialize list of pointers
        int[] subProIters = new int[ProcessList.size()];
        // set all initial pointer valuse to zero
        Arrays.fill(subProIters, 0);
        // find total number of executions
        int totalSubProcessesLen = 0;
        for (int processIdx = 0; processIdx < ProcessList.size(); processIdx++) {
            totalSubProcessesLen += ProcessList.get(processIdx).ProcessEvents.size();
        }
        //Creating the 64 locks
        for(int i=0; i<64; i++){
            stateObj lockObj= new stateObj();
            SixtyFourLocks.add(lockObj);
        }

        for (int numOfExecutions = 0; numOfExecutions < totalSubProcessesLen; numOfExecutions++) {
            SubProcess lowestTimeValueProcess=getLowestTimeValueSubProcess(subProIters,loadingTime);
            /*******************************
             * Logic for 1 Process
             ********************************/
            if(subProIters.length==1){
                loadingTime = lowestTimeValueProcess.CompletionTime;
                eventQueue.add(lowestTimeValueProcess);
            }
            else {
                /*******************************
                 * Logic for multiple Process
                 ********************************/
                //Logic for multiple Process
                if (lowestTimeValueProcess.subProcessName.equals("START")) {
                    if (firstStartFound == false) {
                        loadingTime = lowestTimeValueProcess.CompletionTime;
                        eventQueue.add(lowestTimeValueProcess);
                        if (subProIters[lowestTimeValueProcess.ProcessNumber] < ProcessList.get(lowestTimeValueProcess.ProcessNumber).ProcessEvents.size()) {
                            subProIters[lowestTimeValueProcess.ProcessNumber]++;
                        }
                        firstStartFound = true;
                    } else {
                        eventQueue.add(lowestTimeValueProcess);
                        if (subProIters[lowestTimeValueProcess.ProcessNumber] < ProcessList.get(lowestTimeValueProcess.ProcessNumber).ProcessEvents.size()) {
                            subProIters[lowestTimeValueProcess.ProcessNumber]++;
                        }
                    }
                } else {
                    loadingTime = lowestTimeValueProcess.CompletionTime;
                    if (subProIters[lowestTimeValueProcess.ProcessNumber] < ProcessList.get(lowestTimeValueProcess.ProcessNumber).ProcessEvents.size()) {
                        subProIters[lowestTimeValueProcess.ProcessNumber]++;
                        eventQueue.add(lowestTimeValueProcess);
                    }
                    if(subProIters[lowestTimeValueProcess.ProcessNumber]==ProcessList.get(lowestTimeValueProcess.ProcessNumber).ProcessEvents.size()){
                        subProIters[lowestTimeValueProcess.ProcessNumber]=-1;
                    }
                }
            }
        }
        RoutineLoop();
    }
    /******************************************************************************************************
     * This Function RoutineLoop() Runs the main system routine and logs all of the process status updates
     * As well as calls the differnt routine functions
     *****************************************************************************************************/
    void RoutineLoop(){
        boolean firstStart=false;
        while(!eventQueue.isEmpty()){
            SubProcess currentProcess=eventQueue.remove(0);
            if (currentProcess.subProcessName.equals("START")) {
                if (firstStart == false) {
                    time+=currentProcess.timeRequest;
                    ArrivalTime(currentProcess);
                    firstStart=true;
                }else{
                    ArrivalTime(currentProcess);
                }
            }
            else {
                time += currentProcess.timeRequest;

                if (currentProcess.subProcessName.equals("CPU")) {
                    CoreAvailablity(currentProcess);
                    if (!LockRequestList.isEmpty() && currentProcess.subProcessName.equals("CPU") && currentProcess.ProcessNumber == LockRequestList.get(0).ProcessNumber) {
                        if (currentProcess.LockBelongs == LockRequestList.get(0).LockBelongs) {
                            lockRequest(LockRequestList.remove(0));
                        }
                    }
                } else if (currentProcess.subProcessName.equals("SSD")) {
                    SSDRequest(currentProcess);
                } else if (currentProcess.subProcessName.equals("OUTPUT") || currentProcess.subProcessName.equals("INPUT")) {
                    inputOutputRequest(currentProcess);
                } else {
                    ProcessList.get(currentProcess.ProcessNumber).ProcessState = "Terminated";
                    System.out.println("Process " + currentProcess.ProcessNumber + " is Terminated at " + time + "ms");
                    System.out.println("Current number of busy cores: " + busyCores);
                    System.out.println("Process " + ProcessList.get(currentProcess.ProcessNumber).ProcessNum + " is " + ProcessList.get(currentProcess.ProcessNumber).ProcessState);

                    for (int i = 0; i < ProcessList.size(); i++) {
                        if(ProcessList.get(i).ProcessNum== currentProcess.ProcessNumber){
                            continue;
                        }
                        System.out.println("Process " + ProcessList.get(i).ProcessNum + " is " + ProcessList.get(i).ProcessState);
                    }
                    System.out.println("====================");
                }
            }
//
}
    }
    /*******************************************************************************
     * This Function ArrivleTime() Prints the Arrival status update of a new Process
     *******************************************************************************/

    void ArrivalTime(SubProcess arrivePro){
        System.out.println("Process "+arrivePro.ProcessNumber+" starts at t="+arrivePro.getTimeRequest()+"ms");
        System.out.println("Current number of busy cores: "+busyCores);
        ProcessList.get(arrivePro.ProcessNumber).ProcessState="Ready";
        System.out.println("Process " + ProcessList.get(arrivePro.ProcessNumber).ProcessNum + " is " + ProcessList.get(arrivePro.ProcessNumber).ProcessState);
        for(int i=0; i< ProcessList.size();i++){
            if(ProcessList.get(i).ProcessNum== arrivePro.ProcessNumber){
                continue;
            }
            System.out.println("Process "+ProcessList.get(i).ProcessNum+" is "+ProcessList.get(i).ProcessState);
        }
        System.out.println("====================");


    }
    /*****************************************************************************
     * This Function CoreAvailablity() checks to see if there is a core available
     * if there is it decrements from the total number of cores and increments busy cores
     * and then passes the subprocess to CoreComplete(). else it puts the process in
     * a READY state and then pushes it into the core queue.
     *****************************************************************************/
    public void CoreAvailablity(SubProcess CoreProcessReady1){

        if(numberOfCores>0){
            numberOfCores--;
            busyCores++;
            coreComplete(CoreProcessReady1);
        }
        else{
            ProcessList.get(CoreProcessReady1.ProcessNumber).ProcessState="READY";
            CoreReadyQueue.add(CoreProcessReady1);
        }


    }
    /**********************************************************************************************************************************************************
     * This Function CoreComplete() computes the core completion task after CoreAvailablity() checks and there
     * is a available core. it also checks if anything is in the core queue if there is then it pops from the front of the queue
     * and switches the subprocess's process into a RUNNING state then increment the
     *********************************************************************************************************************************************************/
    void coreComplete(SubProcess CoreProcessReady){
            CoreReadyQueue.add(CoreProcessReady);
            if(! CoreReadyQueue.isEmpty()){
                SubProcess tempProcess= CoreReadyQueue.remove(0);
                ProcessList.get(CoreProcessReady.ProcessNumber).ProcessState="RUNNING";
                numberOfCores++;
                busyCores--;
            }


    }
    /**************************************************************************************
     * This Function inputOutputRequest() adds input and output request to the global time
     * (I do this prior to passing in the input/output. so this function just puts the process
     * into a BLOCKED state.
     *************************************************************************************/
    void inputOutputRequest(SubProcess inputOutputPro){
        ProcessList.get(inputOutputPro.ProcessNumber).ProcessState="BLOCKED";
    }
    /******************************************************************
     * his Function SSDRequest() checks to see if the SSD is available
     *****************************************************************/
    void SSDRequest(SubProcess SSDPro){
        if(numberOFSSD>0){
            numberOFSSD--;
            ssdCompletionEvent(SSDPro);
        }
        else{
            SSDQueue.add(SSDPro);
        }
    }
    /*********************************************************************************************************************************************************
     * This Function ssdCompletionEvent() computes the SSD completion task after SSDRequest() checks and there
     * is a available SSD
     *********************************************************************************************************************************************************/
    void ssdCompletionEvent(SubProcess newSSD){
        SSDQueue.add(newSSD);
        ProcessList.get(newSSD.ProcessNumber).ProcessState="BLOCKED";
        if(!SSDQueue.isEmpty()){
            SubProcess tempProcess=SSDQueue.remove(0);
            numberOFSSD++;
        }
    }
//    void lockRequest(process LockPro){
//        System.out.println("lockRequest Function");
//    }

}
