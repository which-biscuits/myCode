import java.util.concurrent.*;
public class TestExecutor implements Runnable{
    String str;
    int num;
    public TestExecutor(String str, int num){
        this.str = str;
        this.num = num;
    }
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(3);
//        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i=0;i<10;i++) {
            exec.execute(new TestExecutor("Thread"+i,3));
        }
        exec.shutdown();
    }
    public void run(){
        for (int index=0;index<num;index++) {
            System.out.print(str);
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){}
        }
    }
}
