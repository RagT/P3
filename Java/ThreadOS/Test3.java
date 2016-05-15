import java.util.Date;

class Test3 extends Thread {

   private int pairs;

   public Test3 ( String args[] ) {
      pairs = Integer.parseInt( args[0] );
   }

   public void run( ) {
      String[] args1;
      String[] args2;

      long startTime = (new Date( ) ).getTime( );
      for ( int i = 0; i < pairs; i++ ) {

         int threadNum = i+1;
         args1 = SysLib.stringToArgs( "TestThread3 Computaionally_Intensive_Test " + threadNum); // computation intensive
         args2 = SysLib.stringToArgs( "TestThread3 Disk_Intensive_Test " + threadNum); 		// disk intensive

         SysLib.exec( args1 );
         SysLib.exec( args2 );
      }
      for (int i = 0; i < pairs * 2; i++ )
         SysLib.join( );
      long endTime = (new Date( ) ).getTime( );

      SysLib.cout( "elapsed time = " + ( endTime - startTime ) + " msec.\n" );
      SysLib.exit( );
   }
}
