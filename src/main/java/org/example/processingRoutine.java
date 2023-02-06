package org.example;

import java.util.ArrayList;

public class processingRoutine {
    int markBusy;
    int time=0;
    int numberOfCores=1;
    ArrayList<process>ProcessList = new ArrayList<>();
    ArrayList<core>CoreList= new ArrayList<>();

    ArrayList<process> coreReadyQueue= new ArrayList<>();
    void CreateProcessObj(String read){
        int processNum=0;
        process currentProcess =new process();
        String[] split1;
//        System.out.println(read);
//        System.out.println("=================");
        read.trim();
        split1=read.split(" ",5);

//        if(split1[0]=="START"){
//            processNum++;
//            currentProcess.processNum = processNum;
//            currentProcess.timeRequest = Integer.parseInt(split1[split1.length - 1]);
//            currentProcess.subProcessName = split1[0];
//            ProcessList.add(currentProcess);
//        }
//        else if (split1[0]=="NCORES"){
//            int i=0;
//            int coreCreater=Integer.parseInt(split1[split1.length-1]);
//            numberOfCores=Integer.parseInt(split1[split1.length-1]);
//            while(i<coreCreater){
//                core currentCore= new core();
//                CoreList.add(currentCore);
//            }
//        }
//        else if(split1[0]=="CPU") {
//            currentProcess.processNum = processNum;
//            currentProcess.timeRequest = Integer.parseInt(split1[split1.length - 1]);
//            currentProcess.subProcessName = split1[0];
//            ProcessList.add(currentProcess);
//        }
//        else if(split1[0]=="SSD"){
//            currentProcess.processNum = processNum;
//            currentProcess.timeRequest = Integer.parseInt(split1[split1.length - 1]);
//            currentProcess.subProcessName = split1[0];
//            ProcessList.add(currentProcess);
//        }
//        else if(split1[0]=="OUTPUT"||split1[0]=="INPUT"){
//            currentProcess.processNum = processNum;
//            currentProcess.timeRequest = Integer.parseInt(split1[split1.length - 1]);
//            currentProcess.subProcessName = split1[0];
//            ProcessList.add(currentProcess);
//        }
//        else if(split1[0]=="END"){
//            currentProcess.processNum = processNum;
//            currentProcess.subProcessName = split1[0];
//        }


            System.out.println("Name: "+split1[0]);
            System.out.println("Arrivle time: "+split1[split1.length-1]);
        System.out.println("=================");



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
                System.out.println("Process "+ currentProcess.processNum+" is terminaated at "+time+"ms");
            }
        }

    }
}
