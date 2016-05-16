import java.util.*;
/*
Raghu Tirumala
Test3.java

Write a user-level test thread called Test3.java which spawns and waits for the completion of X pairs of threads
(where X = 1 ~ 4), one conducting only numerical computation and the other reading/writing many blocks randomly
across the disk.
*/
class Test3 extends Thread {
    private int pairs;
    private long startTime;
    private long totalTime;

    public Test3( String[] args ) {
        pairs = Integer.parseInt(args[0]);
        startTime =  new Date().getTime();
    }

    public void run() {
        Map<Integer,String> tasks = new HashMap<Integer,String>(); //Stores tasks executed by which thread

        //Makes threads execute computational and disk tasks
        for (int i = 0; i < pairs; i++) {
            String[] thread1 = SysLib.stringToArgs("TestThread3 CompTest 10000");
            String[] thread2 = SysLib.stringToArgs("TestThread3 DiskTest 5000");

            tasks.put(SysLib.exec(thread1),"Computation"); //Executes computational task thread
            tasks.put(SysLib.exec(thread2),"Disk Task"); //Executes Disk Read/Write Task thread
        }


        for (int i = 0; i < pairs * 2; i++) {
            String type = tasks.remove(SysLib.join());
            SysLib.cout(type +" finished in " + ( new Date().getTime()- startTime) + "ms\n");
        }

        totalTime =  new Date().getTime() - startTime;
        SysLib.cout("elapsed time = " + totalTime + "ms\n");
        SysLib.exit();
    }
}
