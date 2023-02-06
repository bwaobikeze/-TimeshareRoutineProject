package org.example;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        int count=0;
        processingRoutine beginProcess= new processingRoutine();
        ArrayList<process> ProcessList = new ArrayList<>();
        //System.out.println("Hello world2!");
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
//        while(!ProcessList.isEmpty()){
//            process currentProcessInLoop= new process();
//            currentProcessInLoop=ProcessList.remove(0);
//            beginProcess.RoutineLoop(currentProcessInLoop);
//        }
        for(int i=0; i<ProcessList.size();i++){
            System.out.println("Process Number:"+ProcessList.get(i).processNum);
            System.out.println("Sub Process: "+ProcessList.get(i).subProcessName);
            System.out.println("Arrival Time: "+ProcessList.get(i).timeRequest);
            System.out.println("=================");

        }


    }
}