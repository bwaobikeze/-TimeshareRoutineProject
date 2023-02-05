package org.example;

import java.util.ArrayList;

public class processingRoutine {
    int markBusy;
    int time=0;
    int numberOfCores=1;
    ArrayList<process>ProcessList = new ArrayList<>();
    ArrayList<core>CoreList= new ArrayList<>(){{
        core cor1 = new core();
        add(cor1);
    }

    };

    ArrayList<process> coreReadyQueue= new ArrayList<>();
    void CreateProcessObj(String read){
        System.out.println(read.charAt(0));

    }


    void ArrivleTime(process arrivePro){
        ProcessList.add(arrivePro);
        //RoutineLoop();
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



        //System.out.println("coreRequest Function");
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
    void RoutineLoop(){
        while(!ProcessList.isEmpty()){
            process currentProcess=ProcessList.remove(0);
            if(currentProcess.subProcessName=="START"){
                ArrivleTime(currentProcess);
            }
            else if(currentProcess.subProcessName=="CPU"){
                coreComplete(currentProcess);
            }
            else if(currentProcess.subProcessName=="SSD"){
                SSDRequest(currentProcess);
            }
            else if(currentProcess.subProcessName=="OUTPUT"||currentProcess.subProcessName=="INPUT"){
                inputOutputRequest(currentProcess);
            }
            else{
                System.out.println("Process"+ currentProcess.processNum+"is terminaated at "+time+"ms");
            }
        }

    }
}
