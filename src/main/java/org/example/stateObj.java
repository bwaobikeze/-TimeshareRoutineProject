package org.example;

import java.util.ArrayList;

public class stateObj {
    boolean LockStatus=false;
    SubProcess currentLockedProcess;
    int SubProcessDoneAt;
    ArrayList<SubProcess> InsertList= new ArrayList<>();

}
