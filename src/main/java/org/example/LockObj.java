package org.example;

import java.util.ArrayList;

public class LockObj {
    boolean availabilty;
    int SubProcessDoneAt;
    ArrayList<SubProcess> LockReadyQueue= new ArrayList<>();
}
