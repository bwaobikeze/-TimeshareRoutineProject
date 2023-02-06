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
    public void CoreAvailablity(process CoreProcess){
        for( int i=0; i< CoreList.size();i++){
            if(CoreList.get(i).availabilty==true){
               numberOfCores--;
               markBusy=i;
                CoreList.get(i).availabilty=false;
               coreComplete(CoreProcess);
            }
            else{
                coreReadyQueue.add(CoreProcess);
            }
        }

    }

    void coreComplete(process CoreProcessReady){
        coreReadyQueue.add(CoreProcessReady);

        process tempCoreProssed;
        if(!coreReadyQueue.isEmpty()){
            tempCoreProssed=coreReadyQueue.remove(0);
            time=time+ tempCoreProssed.timeRequest;
            numberOfCores++;
            CoreList.get(markBusy).availabilty=true;
        }
        else{
            numberOfCores++;
            CoreList.get(markBusy).availabilty=true;
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
