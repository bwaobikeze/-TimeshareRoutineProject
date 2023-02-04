package org.example;

import java.util.ArrayList;

public class processingRoutine {
    int markBusy;
    int time=0;
    int numberOfCores;
    ArrayList<process>ProcessList = new ArrayList<>();
    ArrayList<core>CoreList= new ArrayList<>();

    ArrayList<process> coreReadyQueue= new ArrayList<>();
    void CreateProcessObj(String read){
        System.out.println(read.charAt(0));

    }

//    void RoutineLoop(){
//        while(!ProcessList.isEmpty()){
//            process currentProcess=ProcessList.remove(0);
//            if(currentProcess.subProcessName=="START"){
//                ArrivleTime(currentProcess);
//            }
//            else if(currentProcess.subProcessName=="CPU"){
//                coreComplete(currentProcess);
//            }
//            else if(currentProcess.subProcessName=="SSD"){
//                SSDRequest(currentProcess);
//            }
//            else if(currentProcess.subProcessName=="OUTPUT"||currentProcess.subProcessName=="INPUT"){
//                inputOutputRequest(currentProcess);
//            }
//            else{
//                System.out.println("Process"+ currentProcess.processNum+"is terminaated!");
//            }
//        }
//
//    }
//    void ArrivleTime(process arrivePro){
//        int busyCores=0;
//        String processInReadyQueue="";
//        for(int i =0; i<CoreList.size(); i++){
//            if(CoreList.get(i).availabilty==false){
//                busyCores++;
//            }
//        }
//        for(int j=0; j<coreReadyQueue.size();j++){
//            processInReadyQueue+=Integer.toString(coreReadyQueue.get(j).processNum);
//            processInReadyQueue+=",";
//
//        }
//        System.out.println("Process "+arrivePro.processNum+" starts at t="+arrivePro.timeRequest+"ms");
//
//        System.out.println("Current number of busy cores: "+busyCores);
//        System.out.println("Ready Queue has Processes "+processInReadyQueue);
//
//
//
//    }
    public boolean CoreAvailablity(){
        for( int i=0; i< CoreList.size();i++){
            if(CoreList.get(i).availabilty==true){
                markBusy=i;
                return true;
            }
        }
        return false;
    }

    void coreComplete(process CoreProcess){
        coreReadyQueue.add(CoreProcess);
        process tempCoreProssed;
        if(CoreAvailablity()==true){
            CoreList.get(markBusy).availabilty=false;
            numberOfCores--;
            if(!coreReadyQueue.isEmpty()){
                tempCoreProssed=coreReadyQueue.remove(0);
                time=time+ tempCoreProssed.timeRequest;
                CoreList.get(markBusy).availabilty=true;
                numberOfCores++;
            }
        }




        //System.out.println("coreRequest Function");
    }
//    void inputOutputRequest(process inputOutputPro){
//        System.out.println("inputOutputRequest Function");
//    }
//    void SSDRequest(process SSDPro){
//        System.out.println("SSDRequest Function");
//    }
//    void lockRequest(process LockPro){
//        System.out.println("lockRequest Function");
//    }
}
