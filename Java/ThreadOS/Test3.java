import java.util.Date;
/*
Raghu Tirumala
Test3.java

Write a user-level test thread called Test3.java which spawns and waits for the completion of X pairs of threads
(where X = 1 ~ 4), one conducting only numerical computation and the other reading/writing many blocks randomly
across the disk. Those two types of threads may be written in TestThread3a.java and TestThread3b.java separately or
written in TestThread3.java that receives an argument and conducts computation or disk
accesses according to the argument.
*/
class Test3 extends Thread {

    private int pairs;
    private long startingTime;
    private long endingTime;

    public Test3 ( String args[] ) {
        pairs = Integer.parseInt( args[0] );
    }

    public void run( ) {
        String[] args1;
        String[] args2;

        startingTime = (new Date( ) ).getTime( );

        String [] numerical = SysLib.stringToArgs("TestThread3a");
        String [] readWrite = SysLib.stringToArgs("TestThread3b");
        for(int i = 0; i < pairs; i++) { //Run TestTread3a
            SysLib.exec(numerical);
        }

        for (int i = 0; i < pairs; i++) { //Run TestThread3b
            SysLib.exec(readWrite);
        }

        //Numerical Work
        for(int i = 0; i < pairs; i++) {
            SysLib.join();
            SysLib.cout("computation completed.\n");
        }

        //reading/writing
        for(int i = 0; i < pairs; i++) {
            SysLib.join();
            SysLib.cout("read/write completed.\n");
        }
        endingTime = (new Date( ) ).getTime( );

        SysLib.cout( "elapsed time = " + ( endingTime - startingTime ) + " msec.\n" );
        SysLib.exit( );
    }
}
