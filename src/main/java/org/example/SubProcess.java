package org.example;
/*************************************************************************************************************************
 * These are the properties to this SubProcess Class
 **************************************************************************************************************************/
public class SubProcess {
    String subProcessName;
    int timeRequest;
    int arrivleTime;
    int ProcessNumber;
    int CompletionTime;
    int LockBelongs;
    int getTimeRequest(){
        return timeRequest;
    }
    String getSubProcessName(){
        return subProcessName;
    }
}
