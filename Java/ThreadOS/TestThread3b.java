/**
 * TestThread3b
 * performs read write operations
 */
public class TestThread3b {
    private byte[] bytesToUse;

    TestThread3b(){
        bytesToUse = new byte[512];
    }

    public void run(){
        for(int i = 0; i < 512; i++){
            SysLib.rawwrite(i,bytesToUse);
            SysLib.rawread(i,bytesToUse);
        }
        SysLib.exit();
    }
}
