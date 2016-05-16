import java.util.*;
/**
*Runs either a computational or disk task
 * for a certain umber of times based on args passed.
 */
public class TestThread3 extends Thread {
    private String testType;
    private byte[] bytes;
    private Random rand;
    private int num; //number of times to run operation
    final int diskSize = 1000;

    public TestThread3(String[] args) {
        testType = args[0];
        num = Integer.parseInt(args[1]);
        bytes = new byte[Disk.blockSize];
        rand = new Random();
    }

    public void run() {
        if (testType.equals("CompTest")) { //Run computation
            RunComp();
        } else if(testType.equals("DiskTest")) {			//Run Disk
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
        Syslib.cout("Disk task completed at: " +  new Date().getTime() + "ms.");
    }

    //Computes cumulative sum of factorials from 0 to num-1
    private void RunComp() {
        long sum = 0;
        for(int i = 0; i < num; i++){
            sum += factorial(i);
        }
        Syslib.cout("Computation task completed at: " +  new Date().getTime() + "ms.");
    }

    private int factorial(int n){
        if(n <= 1){
            return 1;
        }
        return n * factorial(n-1);
    }
}