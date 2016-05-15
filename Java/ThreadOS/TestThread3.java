import java.util.Date;

class TestThread3 extends Thread {
   private String threadName;
   private String testName;

   private long startTime, stopTime;
   private long blockStartTime, blockStopTime;
   private long testStartTime, testStopTime;
   private String strOut;

   public TestThread3 ( String args[] ) {
      testName = args[0];
      threadName = args[1];
   }

   public void run( ) {

      if ( testName.equals( "Computaionally_Intensive_Test" ) ) {
         double powerSignal, waveSignal, rmsSignal;
         startTime = (new Date()).getTime();
         rmsSignal = 0.0;

         Integer N = (Integer.MAX_VALUE)/100;
         for (double i = 0.0; i < (double) (N); i = i + 1.0) {
            waveSignal = Math.sin((i / 360.0) * Math.PI);
            powerSignal = Math.pow(waveSignal, 2.0);
            rmsSignal = rmsSignal + Math.sqrt(powerSignal);
         }
         stopTime = (new Date()).getTime();
         SysLib.cout("\n[Thread:" + threadName + "]: ==> AvgRMS Value: "+ rmsSignal/N  +"  Computation Time: " + (stopTime - startTime) + "msec\n");
      }
      else if ( testName.equals( "Disk_Intensive_Test" ) ) {
         byte[] buffer = new byte[512];
         testStartTime = (new Date()).getTime();
         blockStartTime = testStartTime;
         for ( int i = 0; i < 1000; i+=10 ) {

            /******************************************
             *
             * PERFORM YOUR DISK READ/WRITE TESTS HERE
             *
             ***************************************** */
            
            // Print out time stats for each 100 blocks tested
            if ((i != 0) && (i % 100) == 0) {
               blockStopTime = (new Date()).getTime();

               String s = String.format("[Thread:" + threadName + "]: Disk: Block:"+ i + " of 1000 - Time: %.3f sec Avg Block Write/Read Time: %d msec%n",
                     (double)((blockStopTime-blockStartTime)/1000.0),
                     ((blockStopTime-blockStartTime)/100));
               SysLib.cout(s);
               blockStartTime = (new Date()).getTime();
            }
         }
         testStopTime = (new Date()).getTime();
         SysLib.cout("\n[Thread:" + threadName + "]: ==> Testing Disk Time: " + (testStopTime-testStartTime) + "msec\n");
      }
      SysLib.cout("[Thread:" + threadName + "]: " + testName + " finished...\n\n" );
      SysLib.exit( );
   }
}
