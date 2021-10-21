public class TestRunnable implements Runnable {
    String name;
    int num;
    public TestRunnable(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public static void main(String[] args) {
        TestRunnable thread1 = new TestRunnable("thread1",50);
        TestRunnable thread2= new TestRunnable("thread2",50);
        new Thread(thread1).start();
        System.out.println("thread1 started");
        new Thread(thread2).start();
        System.out.println("thread2 started");
    }
    @Override
    public void run() {
        for (int index=0;index<num;index++) {
            System.out.print(name);
        }
    }
}
