import java.util.*;
/**
*Runs either a computational or disk task
 * for a certain number of times based on args passed.
 */
public class TestThread3 extends Thread {
    private String testType;
    private byte[] bytes;
    private int num; //number of times to run operation
    private long startTime;

    public TestThread3(String[] args) {
        testType = args[0];
        num = Integer.parseInt(args[1]);
        startTime = Long.parseLong(args[2]);
        bytes = new byte[Disk.blockSize];
        rand = new Random();
    }

    public void run() {
        if (testType.equals("CompTest")) { //Run computation
            RunComp();
        } else{ //Run Disk task
            RunDisk();
        }
        SysLib.exit();
    }

    //Does some disk reads and writes
    private void RunDisk() {
        for(int i = 0; i < num; i++) {
            SysLib.rawwrite(i, bytes);
            SysLib.rawread(i, bytes);
        }
        SysLib.cout("Disk task completed at: " +
                (new Date().getTime() - startTime) + "ms.\n");
    }

    //Computes cumulative sum of factorials from 0 to num-1
    private void RunComp() {
        long sum = 0;
        for(int i = 0; i < num; i++){
            sum += factorial(i);
        }
        SysLib.cout("Computation task completed at: " +
                (new Date().getTime() - startTime) + "ms.\n");
    }

    private int factorial(int n){
        if(n <= 1){
            return 1;
        }
        return n * factorial(n-1);
    }
}