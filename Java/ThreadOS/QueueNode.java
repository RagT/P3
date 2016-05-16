import java.util.ArrayList;
/**
 *QueueNode.java
 * Used to implement SyncQueue class. Holds each thread inside of a
 * queue represented by an ArrayList and either wakes it up or puts it to sleep
 */
public class QueueNode {
    private ArrayList<Integer>  tids;

    //Default constructor: initializes the queue
    public QueueNode() {
        tids = new ArrayList<Integer>();
    }

    //Puts thread to sleep using wait() method call
    //Returns success/failiure of removal of thread from queue
    public synchronized int putToSleep() {
        if(tids.size() == 0){
            try {
                wait( );
            } catch ( InterruptedException e ) {
                SysLib.cerr("Error" + e.toString() + "\n");  //Error message
            }
            return tids.remove(0);
        }
        return -1;
    }

    public synchronized void wakeUp(int tid) {
        tids.add(tid);
        notify();
    }
}
