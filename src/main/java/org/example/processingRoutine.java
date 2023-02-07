package org.example;

import java.util.ArrayList;

public class processingRoutine {
    int markBusy;
    int time=0;
    int numberOfCores;
    ArrayList<process>SSDQueue = new ArrayList<>();
    ArrayList<core>CoreList= new ArrayList<>();

    ArrayList<process> coreReadyQueue= new ArrayList<>();

void createCores(int numOFCores){
    int i=0;
    numberOfCores=numOFCores;

    while(i<numOFCores){
    core currentCore= new core();
     CoreList.add(currentCore);
      i++;
    }
}

    void RoutineLoop(process currentProcess){
        if(currentProcess.subProcessName.equals("START")){
            ArrivleTime(currentProcess);
        }
        else if(currentProcess.subProcessName.equals("CPU")){
            coreComplete(currentProcess);
        }
//        else if(currentProcess.subProcessName=="SSD"){
//            SSDRequest(currentProcess);
//        }
//        else if(currentProcess.subProcessName=="OUTPUT"||currentProcess.subProcessName=="INPUT"){
//            inputOutputRequest(currentProcess);
//        }
//        else{
//            System.out.println("Process "+ currentProcess.processNum+" is terminaated at "+time+"ms");
//        }


    }

    void ArrivleTime(process arrivePro){

        System.out.println("Process "+arrivePro.processNum+" starts at t="+arrivePro.timeRequest+"ms");
        time=arrivePro.timeRequest;
        int busyCores=0;
        String processInReadyQueue="";
        for(int i =0; i<CoreList.size(); i++){
            if(CoreList.get(i).availabilty==false){
                busyCores++;
            }
        }
        System.out.println("Current number of busy cores: "+busyCores);
        for(int j=0; j<coreReadyQueue.size();j++){
            processInReadyQueue+=Integer.toString(coreReadyQueue.get(j).processNum);
            processInReadyQueue+=",";

        }
        System.out.println("Ready Queue has Processes "+ processInReadyQueue);
        System.out.println("====================");


    }
    public boolean CoreAvailablity(){


        for( int i=0; i< CoreList.size();i++){
            if(CoreList.get(i).availabilty==true){
               markBusy=i;
               return true;
            }

        }
        return false;

    }

    void coreComplete(process CoreProcessReady){
        if(CoreAvailablity()==true){
            numberOfCores--;
            CoreList.get(markBusy).availabilty=false;
            time+=CoreProcessReady.timeRequest;
        }
        else{
            coreReadyQueue.add(CoreProcessReady);
        }
        if(!coreReadyQueue.isEmpty()){
            if(CoreAvailablity()==true){
                process processTemp=coreReadyQueue.remove(0);
                numberOfCores--;
                CoreList.get(markBusy).availabilty=false;
                time+=processTemp.timeRequest;

            }
            else{
                numberOfCores++;
                CoreList.get(markBusy).availabilty=true;
            }
        }
    }
    void inputOutputRequest(process inputOutputPro){
        System.out.println("inputOutputRequest Function");
    }
    void SSDRequest(process SSDPro){
        System.out.println("SSDRequest Function");
    }
    void lockRequest(process LockPro){
        System.out.println("lockRequest Function");
    }

}
