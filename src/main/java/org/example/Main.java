package org.example;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        int count=0;
        processingRoutine beginProcess= new processingRoutine();
        ArrayList<process> ProcessList = new ArrayList<>();
        Scanner input=new Scanner(System.in);
        while(input.hasNext()){
            //count++;
            String text=input.nextLine();
            process currentProcess =new process();
            String[] split1;
            text.trim();
            split1=text.split(" ",5);


            if(split1[0].equals("START")){
               count++;
                currentProcess.processNum = count;
                currentProcess.timeRequest = Integer.parseInt(split1[split1.length - 1]);
                currentProcess.subProcessName = split1[0];
                ProcessList.add(currentProcess);
            }
            else if (split1[0].equals("NCORES")){
                beginProcess.createCores(Integer.parseInt(split1[split1.length-1]));
            }
            else if(split1[0].equals("CPU")) {
                currentProcess.processNum = count;
                currentProcess.timeRequest = Integer.parseInt(split1[split1.length - 1]);
                currentProcess.subProcessName = split1[0];
                ProcessList.add(currentProcess);
            }
            else if(split1[0].equals("SSD")){
                currentProcess.processNum = count;
                currentProcess.timeRequest = Integer.parseInt(split1[split1.length - 1]);
                currentProcess.subProcessName = split1[0];
                ProcessList.add(currentProcess);
            }
            else if(split1[0].equals("OUTPUT")||split1[0].equals("INPUT")){
                currentProcess.processNum = count;
                currentProcess.timeRequest = Integer.parseInt(split1[split1.length - 1]);
                currentProcess.subProcessName = split1[0];
                ProcessList.add(currentProcess);
            }
            else if(split1[0].equals("END")){
                currentProcess.processNum = count;
                currentProcess.subProcessName = split1[0];
                ProcessList.add(currentProcess);
            }

        }
        while(!ProcessList.isEmpty()){
            process currentProcessInLoop=ProcessList.remove(0);
            beginProcess.RoutineLoop(currentProcessInLoop);
        }
        System.out.println(" Current Time: "+beginProcess.time+"ms");

//        for(int j=0; j< beginProcess.coreReadyQueue.size();j++){
//            System.out.println("Process Number:"+ beginProcess.coreReadyQueue.get(j).processNum);
//            System.out.println("Sub Process: "+ beginProcess.coreReadyQueue.get(j).subProcessName);
//            System.out.println("Arrival Time: "+ beginProcess.coreReadyQueue.get(j).timeRequest);
//            System.out.println("=================");
//        }
//        while(itr.hasNext()){
//            process currentPro=itr.next();
//            itr.remove();
//            beginProcess.RoutineLoop(currentPro);
//        }
//        System.out.println(ProcessList.peek().subProcessName);
//        ProcessList.poll();
//        System.out.println(ProcessList.peek().subProcessName);
        //        for(int i=0; i<ProcessList.size();i++){
//            process currentProcessInLoop= new process();
//            currentProcessInLoop=ProcessList.remove();
//            System.out.println("Process Number:"+ currentProcessInLoop.processNum);
//            System.out.println("Sub Process: "+ currentProcessInLoop.subProcessName);
//            System.out.println("Arrival Time: "+ currentProcessInLoop.timeRequest);
//            System.out.println("=================");
//
//        }



    }
}
