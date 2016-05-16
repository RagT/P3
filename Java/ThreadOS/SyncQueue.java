/**
 *Raghu Tirumala
 * SyncQueue.java
 *
 * Class Used in Kernel class to implement Wait/Exit methods
 */
public class SyncQueue {
    private QueueNode[] queue;
    private static final int DEFAULTCOND = 10;

    //Initializes queue with default number of  max conditions
    public SyncQueue() {
        this(DEFAULTCOND);
    }

    //Initializes SyncQueue with specified number of max conditions
    public SyncQueue(int maxCond) {
        queue = new QueueNode[maxCond];
        for(int i = 0; i < maxCond; i++){
            queue[i] = new QueueNode();
        }
    }

    /*
    Dequeues and wakes up a thread waiting for a given condition.
    If there are two or more threads waiting for the same condition, only one thread is dequeued and resumed.
    The FCFS (first-come-first-service) order does not matter.
    @param tid is the specific thread number that will be woken up
     */
    public void dequeueAndWakeUp(int condition, int tid) {
        if(queue[condition] == null) {
            queue[condition] = new QueueNode();
        }
        queue[condition].wakeUp(tid);
    }

    //Default tid is 0
    public void dequeueAndWakeUp(int condition){
        dequeueAndWakeUp(condition, 0);
    }


    //allows a calling thread to sleep until a given condition is satisfied
    public int enqueueAndSleep(int condition) {
        if(queue[condition] == null) {
            queue[condition] = new QueueNode();
        }
        return queue[condition].putToSleep();
    }
}
