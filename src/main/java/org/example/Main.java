package org.example;
import java.io.File;
import java.util.*;
/**********************************************
 * Brian Waobikeze
 * Date: 2/26/2023
 * COSC 3360 Fundamentals of Operating Systems
 * Professor: Jehan-Francois Paris
 * Section #: 10716
 ***********************************************/

public class Main {

    public static void main(String[] args) {
        int seenStartOnce=0;
        int count=0;
        int LockCpu=0;
        processingRoutine beginProcess= new processingRoutine();
        try {
            File myObj = new File("S:\\TimeSharingProcess\\src\\main\\java\\org\\example\\read.txt");
            Scanner input = new Scanner(myObj);
            while (input.hasNext()) {
                String text = input.nextLine();
                String[] split1;
                text.trim();
                split1 = text.split("\\s+", 5);
                if (split1[0].equals("NCORES")) {
                    int incrementIter=0;
                    while(incrementIter<Integer.parseInt(split1[split1.length - 1])){
                        stateObj core=new stateObj();
                        beginProcess.CoreList.add(core);
                        incrementIter++;
                    }
                    beginProcess.numberOfCores = (Integer.parseInt(split1[split1.length - 1]));
                    continue;
                } else if (split1[0].equals("START")) {
                    if (seenStartOnce == 0) {
                        process createNew = new process();
                        createNew.ProcessNum = count;
                        SubProcess currentProcess = new SubProcess();
                        currentProcess.timeRequest = Integer.parseInt(split1[split1.length - 1]);
                        currentProcess.subProcessName = split1[0];
                        createNew.ProcessEvents.add(currentProcess);
                        beginProcess.ProcessList.add(createNew);
                        seenStartOnce++;
                    } else {
                        process createNew = new process();
                        count++;
                        createNew.ProcessNum = count;
                        SubProcess currentProcess = new SubProcess();
                        currentProcess.ProcessNumber=count;
                        currentProcess.timeRequest = Integer.parseInt(split1[split1.length - 1]);
                        currentProcess.subProcessName = split1[0];
                        createNew.ProcessEvents.add(currentProcess);
                        beginProcess.ProcessList.add(createNew);

                    }

                } else if (split1[0].equals("CPU")) {
                    SubProcess currentProcess = new SubProcess();
                    currentProcess.timeRequest = Integer.parseInt(split1[split1.length - 1]);
                    currentProcess.subProcessName = split1[0];
                    currentProcess.ProcessNumber=count;
                    currentProcess.LockBelongs=LockCpu;
                    beginProcess.ProcessList.get(count).ProcessEvents.add(currentProcess);
                    LockCpu++;
                } else if (split1[0].equals("SSD")) {
                    SubProcess currentProcess = new SubProcess();
                    currentProcess.timeRequest = Integer.parseInt(split1[split1.length - 1]);
                    currentProcess.subProcessName = split1[0];
                    currentProcess.ProcessNumber=count;
                    beginProcess.ProcessList.get(count).ProcessEvents.add(currentProcess);
                } else if (split1[0].equals("OUTPUT") || split1[0].equals("INPUT")) {
                    SubProcess currentProcess = new SubProcess();
                    currentProcess.timeRequest = Integer.parseInt(split1[split1.length - 1]);
                    currentProcess.subProcessName = split1[0];
                    currentProcess.ProcessNumber=count;
                    beginProcess.ProcessList.get(count).ProcessEvents.add(currentProcess);
                } else if(split1[0].equals("LOCK")||split1[0].equals("UNLOCK")){
                    SubProcess currentProcess = new SubProcess();
                    currentProcess.timeRequest = Integer.parseInt(split1[split1.length - 1]);
                    currentProcess.subProcessName = split1[0];
                    currentProcess.ProcessNumber=count;
                   int eventListSize=beginProcess.ProcessList.get(currentProcess.ProcessNumber).ProcessEvents.size()-1;
                    currentProcess.LockBelongs=beginProcess.ProcessList.get(currentProcess.ProcessNumber).ProcessEvents.get(eventListSize).LockBelongs;
                    beginProcess.LockRequestList.add(currentProcess);
                }
                else if (split1[0].equals("END")) {
                    SubProcess currentProcess = new SubProcess();
                    currentProcess.subProcessName = split1[0];
                    currentProcess.ProcessNumber=count;
                    beginProcess.ProcessList.get(count).ProcessEvents.add(currentProcess);
                }
            }
            input.close();
        } catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }

        beginProcess.creatingEventList();
//        for(int i=0; i<beginProcess.eventQueue.size();i++){
//            System.out.println(beginProcess.eventQueue.get(i).subProcessName);
//            System.out.println(beginProcess.eventQueue.get(i).timeRequest);
//            System.out.println(beginProcess.eventQueue.get(i).ProcessNumber);
//            System.out.println("===============================================");
//        }
    }
}
