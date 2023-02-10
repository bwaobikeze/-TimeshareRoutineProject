package org.example;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        int seenStartOnce=0;
        int count=0;
        processingRoutine beginProcess= new processingRoutine();
        ArrayList<process> ProcessList = new ArrayList<>();
        Scanner input=new Scanner(System.in);
        while(input.hasNext()){
            //count++;
            String text=input.nextLine();
            String[] split1;
            text.trim();
            split1=text.split(" ",5);
            if (split1[0].equals("NCORES")) {
                beginProcess.numberOfCores = (Integer.parseInt(split1[split1.length - 1]));
                continue;
            }
            else if(split1[0].equals("START")){
                if(seenStartOnce==0){
                    process createNew= new process();
                    createNew.ProcessNum=count;
                    ProcessList.add(createNew);
                    seenStartOnce++;
                    continue;
                }
                else{
                    process createNew= new process();
                    count++;
                    createNew.ProcessNum=count;
                    ProcessList.add(createNew);
                    continue;

                }

                }
            else if(split1[0].equals("CPU")) {
                SubProcess currentProcess= new SubProcess();
                currentProcess.timeRequest = Integer.parseInt(split1[split1.length - 1]);
                currentProcess.subProcessName = split1[0];
                ProcessList.get(count).ProcessEvents.add(currentProcess);
            }
            else if(split1[0].equals("SSD")){
                SubProcess currentProcess= new SubProcess();
                currentProcess.timeRequest = Integer.parseInt(split1[split1.length - 1]);
                currentProcess.subProcessName = split1[0];
                ProcessList.get(count).ProcessEvents.add(currentProcess);
            }
            else if(split1[0].equals("OUTPUT")||split1[0].equals("INPUT")){
                SubProcess currentProcess= new SubProcess();
                currentProcess.timeRequest = Integer.parseInt(split1[split1.length - 1]);
                currentProcess.subProcessName = split1[0];
                ProcessList.get(count).ProcessEvents.add(currentProcess);
            }
            else if(split1[0].equals("END")){
                SubProcess currentProcess= new SubProcess();
                currentProcess.subProcessName = split1[0];
                ProcessList.get(count).ProcessEvents.add(currentProcess);
            }


        }
        System.out.println("Done");
        System.out.println(ProcessList.get(1).ProcessEvents.get(ProcessList.get(1).ProcessEvents.size()-2).subProcessName);
        System.out.println(ProcessList.get(1).ProcessEvents.get(ProcessList.get(1).ProcessEvents.size()-2).timeRequest);
    }
}
