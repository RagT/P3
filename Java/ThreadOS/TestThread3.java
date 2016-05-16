import java.util.*;

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

    // Randomly reads or writes to a random block on the disk
    private void RunDisk() {
        for(int i = 0; i < num; i++) {
            int block = rand.nextInt(diskSize); //pick block
            int option = rand.nextInt(1); //pick whether to read or write
            if (option == 0)
                SysLib.rawread(block, bytes);
            else
                SysLib.rawwrite(block, bytes);

        }
    }


    private void RunComp() {
    }
}