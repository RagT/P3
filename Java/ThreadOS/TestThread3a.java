/**
 * Performs computationally intensive operation
 * Factorial
 */
public class TestThread3a extends Thread{
    public TestThread3a(){
    }

    private int factorial(int n){
        if(n <= 0){
            return 1;
        }
        return n * factorial(n-1);
    }

    public void run(){
        long sum = 0;
        for(int i = 0; i < 75; i++){
            for(int j = 0; j < 50; j++) {
                sum += factorial(i + j);
            }
        }
        SysLib.exit();
    }
}
